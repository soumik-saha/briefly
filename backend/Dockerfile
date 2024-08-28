# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the build.gradle and settings.gradle files
COPY build.gradle settings.gradle ./

# Copy the src directory
COPY src ./src

# Copy the .env file from the resources directory
COPY src/main/resources/.env ./

# Install Gradle
RUN apt-get update && \
    apt-get install -y wget unzip && \
    wget https://services.gradle.org/distributions/gradle-8.10-bin.zip && \
    unzip gradle-8.10-bin.zip && \
    mv gradle-8.10 /opt/gradle && \
    ln -s /opt/gradle/bin/gradle /usr/bin/gradle

# Build the project
RUN gradle build --no-daemon

# Expose the port the app runs on
EXPOSE 9495

# Run the application
CMD ["java", "-jar", "build/libs/backend-0.0.1-SNAPSHOT.jar"]