#FROM openjdk
##COPY . /usr/src/myapp
#WORKDIR ./app
#EXPOSE 8085
#COPY p
#RUN #javac Main.java
#CMD ["java", "Main"]


#FROM openjdk:17
#ARG JAR_FILE=target/*.jar
#WORKDIR /app
#EXPOSE 8085
#COPY ./target/diplom33-0.0.1-SNAPSHOT.jar app.jar
#ENTRYPOINT ["java", "-jar", "/app.jar"]

FROM openjdk:17
ARG JAR_FILE=target/*.jar
WORKDIR /app
EXPOSE 8085
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]