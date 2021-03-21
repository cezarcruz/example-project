FROM openjdk:11
RUN mkdir /app
COPY /target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=${ENV}", "-jar", "/app/app.jar"]