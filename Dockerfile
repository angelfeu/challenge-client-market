FROM amazoncorretto:17

RUN yum -y install tar gzip

ARG CUSTOM_HOME_DIR="/home/user"
ARG CUSTOM_LIBS_FOLDER="${CUSTOM_HOME_DIR}/libs"

# Install Maven
ARG MAVEN_VERSION=3.6.3
ARG MAVEN_BASE_URL="https://apache.osuosl.org/maven/maven-3/${MAVEN_VERSION}/binaries"
ARG MAVEN_SHA="c35a1803a6e70a126e80b2b3ae33eed961f83ed74d18fcd16909b2d44d7dada3203f1ffe726c17ef8dcca2dcaa9fca676987befeadc9b9f759967a8cb77181c0"

ENV MAVEN_HOME /usr/share/maven

# This line copy all data from fetched from the repo tag to a docker container
ADD . /app

WORKDIR /app

# This lines run the building scripts
RUN ["chmod", "+x", "./mvnw"]

RUN ./mvnw clean install -e

RUN mv /app/target/challenge-0.0.1-SNAPSHOT.jar /app/challenge-client-market.jar

EXPOSE 8080

CMD ["java","-jar","/app/challenge-client-market.jar"]