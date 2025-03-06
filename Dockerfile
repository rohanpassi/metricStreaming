FROM openjdk:21-jdk-slim

# Copy the JAR file into the container
COPY target/*.jar app.jar

# Expose the port your Spring Boot application runs on (typically 8080)
EXPOSE 8080

# Specify the command to run when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]