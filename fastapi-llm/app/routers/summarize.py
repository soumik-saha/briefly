from fastapi import APIRouter, HTTPException
from app.services.summarization import summarize_url

router = APIRouter()


@router.get("/summarize")
async def get_summary(url: str):
    try:
        summary = await summarize_url(url)

        # Create a translation table to remove unwanted characters
        unwanted_chars = "\n\t\r\v\f\b\a\0\x1b\x00\x1f\x7f\x1c\x1d\x1e"
        translation_table = str.maketrans("", "", unwanted_chars)

        # Remove unwanted characters and extra whitespace
        summary = summary.translate(translation_table)
        summary = " ".join(summary.split())
        summary = summary.strip()
        return summary
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))