# Use Temurin (modern OpenJDK replacement)
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy your jar file
COPY target/*.jar app.jar

# Run your application
CMD ["java", "-jar", "app.jar"]
