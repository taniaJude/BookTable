FROM mariadb:latest
ENV MYSQL_ROOT_PASSWORD mypassword
ENV MYSQL_DATABASE testdb
ENV MYSQL_USER toto
ENV MYSQL_PASSWORD mypassword
RUN apt-get update && apt-get -y install vim
EXPOSE 3306
CMD ["mysqld"]
