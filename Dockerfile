FROM openjdk:11
EXPOSE 8083
ADD target/tpAchatProject.jar tpAchatProject.jar
ENTRYPOINT ["java","-jar","/tpAchatProject.jar"]