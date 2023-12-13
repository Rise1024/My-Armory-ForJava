FROM openjdk:8-jre
MAINTAINER pdai
WORKDIR /
ADD target/Spring-Boot-Docker.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar"]
CMD ["app.jar"]