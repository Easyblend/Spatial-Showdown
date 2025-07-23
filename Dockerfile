FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy all files into the image
COPY . .

# Run Maven to build the project inside the image
RUN ./mvnw clean package -DskipTests

# Use the generated jar (adjust the name if needed)
CMD ["java", "-jar", "target/your-app-name-0.0.1-SNAPSHOT.jar"]
