from fastapi import FastAPI
from app.routers import summarize
app = FastAPI()


app.include_router(summarize.router)

@app.get("/")
def read_root():
    return {"message": "Welcome to the Breifly Server!"}
