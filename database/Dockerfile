FROM mariadb:latest

COPY setup.sql /docker-entrypoint-initdb.d/
ENV MARIADB_ROOT_PASSWORD=tech-challenge-password
EXPOSE 3306