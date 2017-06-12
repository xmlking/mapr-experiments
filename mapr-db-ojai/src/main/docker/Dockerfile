FROM maprtech/pacc:5.2.1_3.0_centos7
MAINTAINER "Sumanth Chinthagunta"

ARG JAR_NAME
ARG JAVA_OPTS
ARG PORT

#MapR Setup
ADD ssl_truststore /opt/mapr/conf/

#VOLUME /tmp
WORKDIR /app
ADD $JAR_NAME app.jar
RUN touch run.sh
EXPOSE $PORT
ENV PORT=$PORT
ENV JAVA_OPTS=$JAVA_OPTS
#ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Dserver.port=$PORT -Djava.security.egd=file:/dev/./urandom -jar app.jar" ]
CMD ["start", "sh", "-c", "java $JAVA_OPTS -Dserver.port=$PORT -Djava.security.egd=file:/dev/./urandom -jar app.jar" ]

