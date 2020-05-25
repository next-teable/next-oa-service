FROM clouthinkin/jre8 as builder

LABEL maintainer="melthaw@gmail.com"

RUN mkdir -p /code

ADD . /code

WORKDIR /code

RUN cd /code && chmod +x gradlew && ./gradlew -p openapi/server clean build -x test

# final stage
FROM clouthinkin/jre8

LABEL maintainer="melthaw@gmail.com"

COPY --from=builder /code/openapi/server/build/libs/server-1.0.0-SNAPSHOT.jar /server-1.0.0-SNAPSHOT.jar
COPY openapi/server/docker/container-files/docker-entrypoint.sh /

RUN chmod +x /*.sh

EXPOSE 80

WORKDIR /
ENTRYPOINT /docker-entrypoint.sh

HEALTHCHECK --interval=30s --timeout=3s --retries=6 \
            CMD curl -s -f http://localhost/actuator/health | jq .status | grep 'UP' || exit 1

