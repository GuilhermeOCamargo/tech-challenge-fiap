FROM amazoncorretto:17

COPY . .
RUN ./gradlew clean build && cd build/libs && mv techChallenge-* app.jar
WORKDIR build/libs
EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Xmx512m", "-Xms512m", "-jar", "app.jar"]