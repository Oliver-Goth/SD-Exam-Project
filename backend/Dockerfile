# ================================
#   1) Build Stage
# ================================
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copy Maven descriptor first (allows efficient Docker caching)
COPY pom.xml .
COPY src ./src

# Build application (skip tests for faster CI/CD)
RUN mvn -q -e -DskipTests clean package


# ================================
#   2) Runtime Stage (small image)
# ================================
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy JAR file from builder container
COPY --from=build /app/target/*.jar app.jar

# The port your backend uses
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
