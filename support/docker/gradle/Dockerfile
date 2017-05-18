FROM groovy:jdk8
MAINTAINER mattRedBox <matt@redboxresearchdata.com.au>
USER root
WORKDIR /opt
ENV GRADLE_VERSION=3.5 GRADLE_HOME=/opt/gradle
RUN apt-get update -y && \
apt-get install -y openssl && \
wget https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip -O gradle-${GRADLE_VERSION}-bin.zip && \
unzip gradle-${GRADLE_VERSION}-bin.zip && \
ln -s /opt/gradle-${GRADLE_VERSION} ${GRADLE_HOME} && \
ln -s ${GRADLE_HOME}/bin/gradle /usr/bin/gradle && \
rm -f gradle-${GRADLE_VERSION}-bin.zip
USER groovy
WORKDIR /home/groovy
VOLUME ${GRADLE_HOME}/.gradle
RUN gradle --version
CMD ["gradle"]