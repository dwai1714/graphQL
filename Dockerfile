FROM java:openjdk-8-jdk-alpine

MAINTAINER devops@corvesta.com

COPY . /tmp
RUN ls -la /tmp

COPY target/*.jar app/graphql.jar

# Define working directory.
WORKDIR /app

CMD [ "java","-jar","graphql.jar" ]
