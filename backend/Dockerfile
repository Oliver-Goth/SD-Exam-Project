# Use JDK only (no Maven)
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy the prebuilt JAR from your local Maven build
COPY target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
