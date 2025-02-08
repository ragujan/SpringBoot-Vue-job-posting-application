FROM openjdk:17-jdk-alpine
ARG JAR_FILE=build/libs/BookingService-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
EXPOSE 9000
ENTRYPOINT ["java","-jar","/app.jar"]