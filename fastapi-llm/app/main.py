from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware

app = FastAPI()

# Configure CORS
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # Adjust this to the specific origins you want to allow
    allow_credentials=True,
    allow_methods=["*"],  # Adjust this to the specific methods you want to allow
    allow_headers=["*"],  # Adjust this to the specific headers you want to allow
)

# Include your routers
from app.routers import summarize
app.include_router(summarize.router)
@app.get("/")
def read_root():
    return {"message": "Welcome to the Breifly Server!"}
