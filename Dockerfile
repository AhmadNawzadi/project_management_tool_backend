FROM openjdk:17-jdk

WORKDIR /app
COPY target/project_management_tool-0.0.1-SNAPSHOT.jar /app/pmt-backend.jar
EXPOSE 8080


CMD ["java", "-jar", "pmt-backend.jar"]