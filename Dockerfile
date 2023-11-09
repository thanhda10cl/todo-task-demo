FROM openjdk:17.0.1-jdk-slim
ARG JAR_FILE=target/*.jar
COPY target/todo-task-demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT [ "java","-jar","/app.jar" ]
