FROM eclipse-temurin:17-jdk
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} appClient.jar
ENTRYPOINT ["java","-jar","/appClient.jar"]
