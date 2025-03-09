FROM eclipse-temurin:21-jre-alpine
LABEL authors="blubin"
COPY target/user-service*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]