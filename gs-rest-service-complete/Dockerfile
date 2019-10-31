FROM openjdk:8-nanoserver
LABEL maintainer="atest@mailserver.com"
COPY target/gs-rest-service-0.1.0.jar /demo1.jar
EXPOSE 5000
CMD ["java", "-jar", "demo1.jar"]