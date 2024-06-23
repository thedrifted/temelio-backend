FROM openjdk:22-jdk-slim

WORKDIR /app

COPY ./backend /app

RUN ./mvnw clean package -DskipTests

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]