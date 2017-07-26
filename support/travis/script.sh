#!/usr/bin/env bash

[ ! -z "${TEST_NAME}" ] || fail
[ ! -z "${BINTRAY_API_KEY}" ] || fail
cd support/acceptance-testing && docker-compose pull && \
docker-compose run acceptance gradle $TEST_NAME && \
docker cp `docker ps -aqf "name=_acceptance_"`:/home/groovy/acceptance/build/reports /tmp/ && \
tar -cvzf "${TEST_NAME}.tar.gz" /tmp/reports && \
curl -T "${TEST_NAME}.tar.gz" -uqcifdev:$BINTRAY_API_KEY "https://api.bintray.com/content/qcifdev/redboxsmoketest/testreports/${TEST_NAME}/reports/"