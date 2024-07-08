FROM openjdk:17-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=target/organizeit-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
LABEL authors="Leon Schampach"

ENTRYPOINT ["java", "-jar", "/app.jar"]