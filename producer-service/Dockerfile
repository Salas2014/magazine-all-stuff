FROM maven:3.9.5-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .
WORKDIR /app/producer-service
RUN mvn clean package -DskipTests

FROM eclipse-temurin:24-jdk-alpine
WORKDIR /app
COPY --from=builder /app/producer-service/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
