FROM openjdk:latest
COPY build/libs/YoungArtist-0.0.1-SNAPSHOT.jar .
EXPOSE 8081
CMD ["java", "-jar", "YoungArtist-0.0.1-SNAPSHOT.jar"]