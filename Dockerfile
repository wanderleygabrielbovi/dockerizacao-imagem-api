# Etapa 1: Construir a aplicação
FROM gradle:jdk17-alpine AS build

WORKDIR /app

COPY . .

RUN gradle buildFatJar --no-daemon --stacktrace

# Etapa 2: Rodar a aplicação
FROM openjdk:17-alpine

WORKDIR /app

COPY --from=build /app/build/libs/*.jar /app/api-kotlin.jar

CMD ["java", "-jar", "api-kotlin.jar"]
