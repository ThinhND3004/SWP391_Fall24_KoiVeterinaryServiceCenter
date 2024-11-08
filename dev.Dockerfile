FROM maven:3.8.3-openjdk-17-slim
COPY /target/*.jar /app/KoiFishBE.jar
EXPOSE 8089
CMD ["java", "-jar", "/app/KoiFishBE.jar"]
