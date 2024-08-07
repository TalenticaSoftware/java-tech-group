FROM openjdk:21-jdk-slim
VOLUME /tmp
ARG JAR_FILE
COPY target/cache-read-write-through-0.0.2-SNAPSHOT.jar readWriteThroughCache.jar
ENV JAVA_OPTS="-Xmx512m -Xms256m"
ENTRYPOINT ["java","-jar","/readWriteThroughCache.jar"]
