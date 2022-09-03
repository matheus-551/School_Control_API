FROM openjdk

WORKDIR /app

COPY target/School_Control-P1-0.0.1-SNAPSHOT.jar /app/schoolControl.jar

ENTRYPOINT [ "java", "-jar", "schoolControl.jar"]