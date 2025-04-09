FROM eclipse-temurin:17-jdk
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} appBank.jar
ENTRYPOINT ["java","-jar","/appBank.jar"]
