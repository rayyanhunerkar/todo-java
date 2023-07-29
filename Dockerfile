FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)


FROM eclipse-temurin:17-jdk-alpine as app
VOLUME /tmp
WORKDIR /app
ARG DEPENDENCY=/app/target
COPY --from=build ${DEPENDENCY}/todolist-0.0.1-SNAPSHOT.jar /app
ENTRYPOINT ["java", "-jar", "/app/todolist-0.0.1-SNAPSHOT.jar"]

