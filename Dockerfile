# Use a lightweight Java 17 image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the built jar file
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Expose your server port (optional, for local dev clarity)
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java","-jar","app.jar"]
