#!/bin/bash -e
cd ..
gradle clean build -x test
docker build -f docker/Dockerfile -t melthaw/webhookee:dashboard-backend .
docker push melthaw/webhookee:dashboard-backend