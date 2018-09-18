FROM java:8-jdk
MAINTAINER Shisei Hanai<ruimo.uno@gmail.com>

RUN apt-get update
RUN apt-get install apt-transport-https -y
RUN echo "deb https://dl.bintray.com/sbt/debian /" | tee -a /etc/apt/sources.list.d/sbt.list
RUN apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 2EE0EA64E40A89B84B2DF73499E82A75642AC823
RUN apt-get update
RUN apt-get install sbt -y
ENV SBT_OPTS -Xmx256M

RUN echo "cd /var/home" > /dotest.sh
RUN echo "sbt -Dsbt.global.base=.sbt -Dsbt.boot.directory=.sbt -DJOBBROKER_DB_URL='jdbc:log4jdbc:postgresql://db:5432/postgres' -Dsbt.ivy.home=.ivy2 update test" >> /dotest.sh
RUN echo "echo done > /var/home/done" >> /dotest.sh
RUN chmod +x /dotest.sh

VOLUME /var/home

ENTRYPOINT ["/bin/bash", "-c", "/dotest.sh"]