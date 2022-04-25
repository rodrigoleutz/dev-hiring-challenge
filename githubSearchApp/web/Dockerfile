FROM openjdk:11
MAINTAINER "Rodrigo Leutz"
COPY build/libs/API-0.0.1.jar /opt/githubSearchApp/app.jar
WORKDIR /opt/githubSearchApp
ENTRYPOINT ["java","-jar","app.jar"]