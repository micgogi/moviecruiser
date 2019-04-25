FROM java:8-jre
WORKDIR usr/src
ENV MYSQL_DATABASE=movie_database
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=root
ENV MYSQL_CI_URL=jdbc:mysql://localhost:3306/movie_database
ADD ./target/moviecruiserauthenticationservice-0.0.1-SNAPSHOT.jar /usr/src/moviecruiserauthenticationservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","moviecruiserauthenticationservice-0.0.1-SNAPSHOT.jar"]
