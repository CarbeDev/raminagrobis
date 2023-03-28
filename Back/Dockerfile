FROM amazoncorretto:17-alpine-jdk

WORKDIR /app
COPY . .
#RUN ./gradlew build
#RUN ./gradlew --no-daemon bootJar
COPY build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]