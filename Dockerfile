# Use a base image with Java installed
FROM openjdk:17-jdk-slim as build

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY target/DesignPatternsIMP-1.0-SNAPSHOT.jar app.jar

# Expose the application port (change 8080 to the port your app uses)
EXPOSE 8081

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
