import os
import httpx
from bs4 import BeautifulSoup
from dotenv import load_dotenv
from typing import Optional
import cohere

load_dotenv()

COHERE_API_KEY = os.getenv("COHERE_API_KEY")

if not COHERE_API_KEY:
    raise ValueError("COHERE_API_KEY environment variable not set")

co = cohere.Client(COHERE_API_KEY)

MAX_TOKENS = 4081  # Define the maximum allowed tokens


async def summarize_url(url: str) -> str:
    # Fetch the URL
    async with httpx.AsyncClient() as client:
        response = await client.get(url)
        response.raise_for_status()  # Raise an exception for 4xx/5xx status codes

    # Extract the text from the HTML
    soup = BeautifulSoup(response.text, "html.parser")

    # Try extracting text from multiple tags
    tags_to_extract = ["p", "h1", "h2", "h3", "li", "span", "div"]
    page_text = " ".join([tag.get_text(strip=True) for tag in soup.find_all(tags_to_extract)])

    # Debugging: Print the fetched HTML and extracted text
    print("Fetched HTML: " + response.text)
    print("Extracted Text: " + page_text)

    # Check if the extracted text is empty
    if not page_text.strip():
        return "No text found to summarize"

    # Truncate text if it exceeds the token limit
    truncated_text = truncate_text(page_text, MAX_TOKENS)

    # Use the Cohere API to summarize the text
    summary = summarize_text(truncated_text)
    return summary


def truncate_text(text: str, max_tokens: int) -> str:
    # For simplicity, truncate the text by taking the first max_tokens characters
    return text[:max_tokens]  # Note: This is a basic truncation; real token counting may differ


def summarize_text(text: str) -> Optional[str]:
    response = co.generate(
        model='command-light',  # Choose the appropriate model size
        prompt=f"Summarize the following website and don't ask any follow-up questions:\n\n{text}",
        max_tokens=250
    )

    if response.generations:
        return response.generations[0].text.strip()
    else:
        return 'Error summarizing text'
