FROM eclipse-temurin:21-jre-alpine

LABEL authors="blubin"

WORKDIR /app

COPY target/identity-service*.jar app.jar

COPY .env .env

ENTRYPOINT ["java", "-jar", "/app/app.jar"]