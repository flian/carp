FROM maven:3.3.3

ARG CARP_VERSION=0.3.0
ARG CARP_APP=carp-webapp-$CARP_VERSION-SNAPSHOT.jar
ARG CARP_WEBAPP_JAR=./carp-webapp/target/$CARP_APP

ADD ./ /tmp/build/
RUN cd /tmp/build && mvn -q dependency:resolve

#构建应用
RUN cd /tmp/build && mvn -q -DskipTests=true package \
        #拷贝编译结果到指定目录
        && mv $CARP_WEBAPP_JAR /app.jar \
        #清理编译痕迹
        && cd / && rm -rf /tmp/build

VOLUME /tmp
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar","--spring.flyway.enabled=false","--spring.profiles.active=demo","--debug"]