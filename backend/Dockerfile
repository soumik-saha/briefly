# Use an official Gradle image as a build stage
FROM gradle:8.10.0-jdk21 AS builder
WORKDIR /app

# Copy only the necessary files to the builder stage
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle
COPY src ./src

# Build the application
RUN ./gradlew clean build -x test

# Use an official OpenJDK runtime as a base image
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built jar file from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# add postgresql driver
ADD https://jdbc.postgresql.org/download/postgresql-42.2.23.jar /app/postgresql-42.2.23.jar

# Expose the port the app runs on
EXPOSE 9495

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
