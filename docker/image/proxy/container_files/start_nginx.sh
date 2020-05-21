#!/bin/bash -e
envsubst '\$HOST_NAME \$BACKEND_ENDPOINT' < /default.conf.template > /etc/nginx/conf.d/default.conf
nginx -g "daemon off;"
