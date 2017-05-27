## Acceptance-testing

[![Build Status](https://travis-ci.org/qcif/acceptance-testing.svg?branch=master)](https://travis-ci.org/qcif/acceptance-testing)

[![Sauce Test Status](https://saucelabs.com/buildstatus/qcifdev)](https://saucelabs.com/u/qcifdev)

[![Sauce Test Status](https://saucelabs.com/browser-matrix/qcifdev.svg)](https://saucelabs.com/u/qcifdev)


### Environment variables you will need to have in place:
* GEB_BUILD_LOCAL_BASE_URL   (e.g., "http://localhost:9000/redbox")
* SAUCE_USERNAME
* SAUCE_ACCESS_KEY
* (Optional: for local builds with selenium headless testing) GEB_BUILD_DOCKER_BASE_URL  (e.g., "http://docker:9000/redbox", where 'docker' is in /etc/hosts entry created by container to connect to host )
* (Optional: reporting - see https://docs.travis-ci.com/user/deployment/pages/)

