import asyncio
from fastapi import APIRouter, HTTPException
from app.services.summarization import summarize_url

router = APIRouter()

@router.get("/summarize")
async def get_summary(url: str):
    try:
        # Set a timeout of 15 seconds for the summarize_url function
        summary = await asyncio.wait_for(summarize_url(url), timeout=1000.0)

        # Create a translation table to remove unwanted characters
        unwanted_chars = "\n\t\r\v\f\b\a\0\x1b\x00\x1f\x7f\x1c\x1d\x1e"
        translation_table = str.maketrans("", "", unwanted_chars)

        # Remove unwanted characters and extra whitespace
        summary = summary.translate(translation_table)
        summary = " ".join(summary.split())
        summary = summary.strip()
        return summary
    except asyncio.TimeoutError:
        raise HTTPException(status_code=504, detail="Request timed out")
    except Exception as e:
        raise HTTPException(status_code=510, detail=str("An error occurred good night: " + str(e)))