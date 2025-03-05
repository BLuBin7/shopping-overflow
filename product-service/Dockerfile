FROM openjdk:17-jdk-alpine
LABEL authors="blubin"
COPY target/product-service*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]