FROM eclipse-temurin:21.0.2_13-jdk-jammy AS build

ARG JAR_FILE
WORKDIR /build

COPY ${JAR_FILE} app.jar

FROM eclipse-temurin:21.0.2_13-jdk-jammy

RUN addgroup spring-boot-group && adduser --ingroup spring-boot-group spring-boot
USER spring-boot:spring-boot-group

VOLUME /tmp
WORKDIR /application

# Копируем весь JAR-файл целиком
COPY --from=build /build/app.jar ./app.jar

# Запускаем приложение напрямую, без layertools
ENTRYPOINT ["java", "-jar", "app.jar"]