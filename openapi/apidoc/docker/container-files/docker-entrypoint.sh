#!/bin/bash -e
echo "Using JAVA_OPTS=$JAVA_OPTS"
exec java $JAVA_OPTS -jar "/apidoc-1.0.0-SNAPSHOT.jar" "$@"
