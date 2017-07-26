#!/usr/bin/env bash

cd support/acceptance-testing && docker-compose pull && \
docker-compose run acceptance gradle $1 && \
docker cp `docker ps -aqf "name=_acceptance_"`:/home/groovy/acceptance/build/reports /tmp/ && \
tar -cvzf "${1}.tar.gz" /tmp/reports