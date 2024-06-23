FROM openjdk:22-jdk-slim

RUN apt-get update && apt-get install -y git maven

RUN git clone git@github.com:thedrifted/temelio-backend.git .

RUN ./mvnw clean package -DskipTests

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]