FROM openjdk:21-jdk-slim
VOLUME /tmp
ARG JAR_FILE
COPY target/cache-standalone-0.0.1-SNAPSHOT.jar standalone.jar
ENTRYPOINT ["java","-jar","/standalone.jar"]
