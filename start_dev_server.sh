#!/bin/bash -e
cd openapi/server
gradle clean bootRun -Dspring.profiles.active=development
