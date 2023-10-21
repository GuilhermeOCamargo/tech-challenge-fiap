FROM amazoncorretto:17

COPY . .
RUN ./gradlew clean build && cd build/libs && mv techChallenge-* app.jar
WORKDIR build/libs
ENV MARIADB_URL="database_url"
ENV SWAGGER_URL="swagger_url"
ENV MARIADB_USER="database_user"
ENV MARIADB_PASSWORD="database_password"
EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=local", "-Xmx512m", "-Xms512m", "-jar", "app.jar"]