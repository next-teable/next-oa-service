#!/bin/bash -e
cd openapiServer
gradle clean bootRun -PjvmArgs="-Dspring.profiles.active=dev"
