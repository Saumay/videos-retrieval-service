FROM openjdk:8-jdk-alpine
MAINTAINER saumay
COPY target/videos-retrieval-service-server-0.0.1-SNAPSHOT.jar videos-retrieval-service-server-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/videos-retrieval-service-server-0.0.1-SNAPSHOT.jar"]
