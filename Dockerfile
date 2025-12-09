# Use Java 17 runtime image
FROM eclipse-temurin:17-jre-jammy

# Set working directory inside container
WORKDIR /app

# Copy the jar file from target folder into container
COPY target/contact-manager-1.0-jar-with-dependencies.jar app.jar

# Run the jar file when container starts
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
