FROM openjdk

EXPOSE 8081

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} drag-race.jar

ENTRYPOINT ["java","-jar","/drag-race.jar"]