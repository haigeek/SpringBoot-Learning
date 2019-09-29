FROM java:8
VOLUME /tmp
ADD ./springboot-hello/target/hello.jar hello.jar
RUN bash -c 'touch /hello.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/hello.jar"]