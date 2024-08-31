# Briefly

Briefly is a web application that allows users to summarize the content of any website. It consists of three main components: a backend service, a frontend application, and a FastAPI-based LLM service.

## Backend

The backend is a Spring Boot application that provides RESTful APIs for summarizing URLs and retrieving summary history.

### Key Files

- BackendApplication: Main entry point of the backend application.
- SummarizerController: REST controller for handling API requests.
- SummarizerService: Service class for summarizing URLs.

### Running the Backend

To run the backend service, navigate to the [`backend`] directory and use the following command:

./gradlew bootRun

## FastAPI LLM Service

The FastAPI-based LLM service provides the summarization logic using a language model.

### Key Files

- main.py: Main entry point of the FastAPI application.

### Running the FastAPI Service

To run the FastAPI service, navigate to the [`fastapi-llm`] directory and use the following command:

uvicorn app.main:app --reload

## Frontend

The frontend is a React application that provides a user interface for summarizing URLs and viewing summary history.

### Key Files

- index.js: Main entry point of the React application.
- App.js: Main application component.
- Home.jsx: Home page component for summarizing URLs.

### Running the Frontend

To run the frontend application, navigate to the [`frontend`] directory and use the following command:

npm start

## Docker

Each component has its own Dockerfile for containerization. You can build and run the containers using Docker Compose.

### Building and Running with Docker Compose

To build and run all services using Docker Compose, navigate to the root directory and use the following command:

docker-compose up --build

## Helm

Helm charts are provided for deploying the application to a Kubernetes cluster.

### Deploying with Helm

To deploy the application using Helm, navigate to the [`helm`] directory and use the following command:

helm install briefly ./briefly
