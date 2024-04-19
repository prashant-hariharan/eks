FROM openjdk:17-jdk-slim

LABEL maintainer="prashant.hariharan@mathema.de"

ADD ./target/*.jar /app/service.jar

#Java execution
CMD ["java",  "-Xmx512m", "-jar", "/app/service.jar"]
EXPOSE 8080


