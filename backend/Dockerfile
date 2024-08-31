# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-oracle

# Add a wait-for-it script to wait for the database
ADD https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

# Copy the application JAR file
COPY build/libs/backend-0.0.1-SNAPSHOT.jar backendapp.jar

# Expose the port the app runs on
EXPOSE 9495

# Run the application with wait-for-it script
ENTRYPOINT ["sh", "-c", "/wait-for-it.sh postgres_con:5432 -- java -jar backendapp.jar"]