FROM openjdk:11
RUN mkdir /app
ADD /target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=${ENV}", "-jar", "/app/app.jar"]