from fastapi import APIRouter, HTTPException
from app.services.summarization import summarize_url

router = APIRouter()

@router.get("/summarize")
async def get_summary(url: str):
    try:
        summary = await summarize_url(url)
        return {"url": url, "summary": summary}
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))