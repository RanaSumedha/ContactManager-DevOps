# Use official OpenJDK image
FROM openjdk:17

# Create app directory inside container
WORKDIR /app

# Copy the generated JAR file from Maven's target folder
# The wildcard ensures it works no matter the jar name
COPY target/*.jar app.jar

# Run the application
CMD ["java", "-jar", "app.jar"]
