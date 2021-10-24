FROM openjdk:11.0.12-jre-buster
ARG JAR_FILE
ADD target/${JAR_FILE} /usr/share/app.jar
ENTRYPOINT ["java", "-jar", "/usr/share/app.jar"]
