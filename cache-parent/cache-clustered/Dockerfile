FROM openjdk:21-jdk-slim
VOLUME /tmp
ARG JAR_FILE
COPY target/cache-clustered-0.0.1-SNAPSHOT.jar clustered.jar
ENTRYPOINT ["java","-jar","/clustered.jar"]
