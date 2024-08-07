FROM openjdk:21-jdk-slim
VOLUME /tmp
ARG JAR_FILE
COPY target/cache-local-0.0.1-SNAPSHOT.jar localcache.jar
ENTRYPOINT ["java","-jar","/localcache.jar"]
