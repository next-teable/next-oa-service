#!/bin/bash
cd backend/openapi
gradle clean bootRun -PjvmArgs="-Dspring.profiles.active=dev"
