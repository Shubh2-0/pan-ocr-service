# Multi-stage Dockerfile for PAN OCR Service
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn package -DskipTests

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
EXPOSE 8083
COPY --from=builder /app/target/ocr-service-1.0.0.jar app.jar
ENV PORT=8083
ENTRYPOINT ["java", "-jar", "app.jar"]
