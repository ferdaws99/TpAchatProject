FROM openjdk:8
EXPOSE 8083
ADD target/tpachatproject.jar tpachatproject.jar
ENTRYPOINT ["java","-jar","/tpachatproject.jar"]