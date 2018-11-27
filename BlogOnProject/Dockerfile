FROM maven:3.5.4-jdk-8-slim
WORKDIR /usr/src/java-code
COPY . /usr/src/java-code/
RUN mvn package

WORKDIR /usr/src/java-app
RUN cp /usr/src/java-code/target/*.war ./app.war
EXPOSE 8443 8080
CMD ["java", "-jar", "app.war"]

#FROM ubuntu:14.04.5
#FROM maven:3.5.4-jdk-8-slim

#RUN apt-get update && apt-get install -y software-properties-common
#RUN add-apt-repository ppa:webupd8team/java

#RUN echo "oracle-java8-installer shared/accepted-oracle-license-v1-1 boolean true" | debconf-set-selections

#RUN apt-get update && apt-get install -y --force-yes oracle-java8-installer maven
#RUN type mvn
#RUN env | grep 'M.*HOME'
#RUN which java
#RUN echo $JAVA_HOME
#RUN echo $M2
#RUN echo $M2_HOME
#RUN echo $PATH
#RUN ls -ltr  /usr/share/maven/boot/
#RUN rm /usr/share/maven/boot/plexus-classworlds-2.5.2.jar
#ADD . /usr/local/blogon
#RUN mvn -f /home/ubuntu/git-project/BlogOnProject/pom.xml package -e

#WORKDIR /usr/src/java-app
#RUN cp ./*.war ./app.war
#EXPOSE 8443 8080
#CMD ["java", "-jar", "app.war"]
