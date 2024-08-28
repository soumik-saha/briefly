<<<<<<< HEAD
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
=======
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
>>>>>>> 17c85cc4d83eaa316f576cbea3adbf53ec82e44a

# Expose the port the app runs on
EXPOSE 9495

# Run the application
<<<<<<< HEAD
CMD ["java", "-jar", "build/libs/backend-0.0.1-SNAPSHOT.jar"]
=======
ENTRYPOINT ["java", "-jar", "app.jar"]
>>>>>>> 17c85cc4d83eaa316f576cbea3adbf53ec82e44a
