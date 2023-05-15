# #base image
# FROM openjdk:17
# EXPOSE 8080
# COPY . /src/main/java
# WORKDIR /src/main/java
# RUN javac ImageClassificatorApplication.java
# ENTRYPOINT ["java", "ImageClassificatorApplication"]