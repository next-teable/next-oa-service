#!/bin/bash -e

# start service accordingly
JAR_VERSION=1.0.0-SNAPSHOT

echo "Using JAVA_OPTS=$JAVA_OPTS"
echo "CMD Args: $@"

java $JAVA_OPTS -jar "/app-openapi-${JAR_VERSION}.jar" "$@"
