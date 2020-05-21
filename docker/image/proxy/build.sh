#!/bin/bash -e
docker build -f Dockerfile -t melthaw/webhookee:dashboard-proxy .
docker push melthaw/webhookee:dashboard-proxy
