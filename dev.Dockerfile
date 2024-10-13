FROM maven:3.8.3-openjdk-17-slim
COPY /target/*.jar /app/KoiFishBE.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/KoiFishBE.jar"]