FROM openjdk:26-ea-1-jdk-oracle
COPY target/gymus-server-java-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]