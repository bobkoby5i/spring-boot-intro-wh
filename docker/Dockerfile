FROM openjdk:11-jdk
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=spring-boot-intro-wh.jar
ADD ${JAR_FILE} spring-boot-intro-wh.jar
CMD java -jar \
    -Djava.security.egd=file:/dev/./urandom \
    -Dspring.profiles.active=$ACTIVE_PROFILE \
##    -Dspring.cloud.config.uri=$CONFIGURATION_SERVER_URI \
    spring-boot-intro-wh.jar

