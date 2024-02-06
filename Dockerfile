FROM adoptopenjdk:17-jdk-hotspot AS builder
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]