FROM openjdk:11
EXPOSE 8083
ADD target/TpAchat.jar TpAchat.jar
ENTRYPOINT ["java","-jar","/TpAchat.jar"]