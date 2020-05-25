FROM clouthinkin/jdk8 as builder

LABEL maintainer="melthaw@gmail.com"

RUN mkdir -p /code

ADD . /code

WORKDIR /code

RUN cd /code && chmod +x gradlew && ./gradlew -p openapi/apidoc clean build -x test

# final stage
FROM clouthinkin/jdk8

LABEL maintainer="melthaw@gmail.com"

COPY --from=builder /code/openapi/apidoc/build/libs/apidoc-1.0.0-SNAPSHOT.jar /apidoc-1.0.0-SNAPSHOT.jar
COPY openapi/apidoc/docker/container-files/docker-entrypoint.sh /

RUN chmod +x /*.sh

EXPOSE 8081

WORKDIR /
ENTRYPOINT /docker-entrypoint.sh
