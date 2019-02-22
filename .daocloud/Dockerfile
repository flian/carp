FROM openjdk:11

ARG WORK_DIR=/daocloud/builder/src

ARG CARP_VERSION=0.3.0
ARG CARP_APP=carp-webapp-$CARP_VERSION-SNAPSHOT.jar
ADD $WORK_DIR/carp-webapp/target/$CARP_APP ./
# run
ENTRYPOINT ["java", "-jar", "$CARP_APP", "--spring.profiles.active=dev"]


