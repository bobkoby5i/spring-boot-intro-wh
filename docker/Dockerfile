#FROM openjdk:8-jdk-alpine
FROM openjdk:11-jdk
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=spring-boot-intro-wh.jar
ADD ${JAR_FILE} spring-boot-intro-wh.jar
#COPY ${JAR_FILE} spring-boot-intro-wh.jar
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/spring-boot-intro-wh.jar"]
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/spring-boot-intro-wh.jar"]

