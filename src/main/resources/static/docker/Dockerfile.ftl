FROM openjdk:8-jdk-alpine
RUN  apk update && apk upgrade && apk add netcat-openbsd
ADD ${name}.jar /usr/local/${name}.jar
CMD java -Djava.security.egd=file:/dev/./urandom -jar /usr/local/${name}.jar
