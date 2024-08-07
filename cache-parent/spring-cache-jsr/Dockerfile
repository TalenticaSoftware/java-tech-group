FROM openjdk:21-jdk-slim
VOLUME /tmp
ARG JAR_FILE
COPY target/spring-cache-jsr-0.0.1-SNAPSHOT.jar spring-cache-jsr.jar
ENV JAVA_OPTS="-Xmx512m -Xms256m"
ENTRYPOINT ["java","-jar","/spring-cache-jsr.jar"]
