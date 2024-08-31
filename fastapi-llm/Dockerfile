# Use an official Python runtime as a parent image
<<<<<<< HEAD
FROM python:3.11-slim
=======
FROM python:3.10-slim
>>>>>>> 17c85cc4d83eaa316f576cbea3adbf53ec82e44a

# Set the working directory in the container
WORKDIR /app

# Copy the requirements file into the container
COPY requirements.txt .

# Install any needed packages specified in requirements.txt
RUN pip install --no-cache-dir -r requirements.txt

# Copy the rest of the application code into the container
COPY . .

<<<<<<< HEAD
# Make port 80 available to the world outside this container
EXPOSE 80

# Run the application
CMD ["uvicorn", "app.main:app", "--host", "0.0.0.0", "--port", "80"]
=======
# Ensure the .env file is copied
COPY .env .env

# Expose the port the app runs on
EXPOSE 8000

# Run the application
CMD ["uvicorn", "app.main:app", "--host", "0.0.0.0", "--port", "8000"]
>>>>>>> 17c85cc4d83eaa316f576cbea3adbf53ec82e44a
