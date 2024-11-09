FROM maven:3.8.3-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml ./
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests -T1000

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar /app/KoiFishBE.jar

EXPOSE 8089

CMD ["java", "-jar", "/app/KoiFishBE.jar"]
