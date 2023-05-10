#base image
FROM openjdk:17
COPY . /src/main/java
WORKDIR /src/main/java
RUN javac ImageClassificatorApplication.java
ENTRYPOINT ["java", "ImageClassificatorApplication"]