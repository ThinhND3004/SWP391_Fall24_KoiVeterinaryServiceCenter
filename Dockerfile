#FROM openjdk:17-alpine AS build
#
## Set the working directory in the build stage
#ENV HOME=/app
#RUN mkdir -p $HOME
#WORKDIR $HOME
#ADD . $HOME
#
## Package the application
#RUN --mount=type=cache,target=/root/.m2 ./mvnw -f $HOME/pom.xml clean package -DskipTests

FROM openjdk:17-alpine

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Set the working directory for the runtime
WORKDIR /app

COPY target/swp391_fall24_be-*-SNAPSHOT.jar /app/KoiFishBE.jar

# Source the environment variables and run the application
ENTRYPOINT ["java", "-Xms2G", "-Xmx2G", "-jar", "/app/KoiFishBE.jar"]

# Expose port 8080 to the outside world
EXPOSE 8080