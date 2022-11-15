FROM openjdk:11
EXPOSE 8083
ADD target/tpachat.jar tpachat.jar
ENTRYPOINT ["java","-jar","/tpachat.jar"]