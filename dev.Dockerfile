# Use Maven image to build and run the application
FROM maven:3.8.3-openjdk-17-slim
WORKDIR /app
COPY pom.xml ./
RUN mvn dependency:go-offline

COPY src ./src

CMD ["mvn", "clean", "spring-boot:run"]
