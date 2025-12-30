# ---------- BUILD STAGE ----------
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /build
COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2 \
    mvn dependency:resolve-plugins dependency:resolve -B
COPY src ./src
RUN --mount=type=cache,target=/root/.m2 \
    mvn package -DskipTests -B

# ---------- RUNTIME STAGE ----------
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /build/target/*.jar app.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","/app/app.jar"]
