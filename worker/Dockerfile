FROM java:8-jdk
MAINTAINER Shisei Hanai<ruimo.uno@gmail.com>

RUN apt-get update
RUN useradd -d "/var/home" -s /bin/bash wkuser
RUN mkdir -p /opt/worker
ADD target/universal /opt/worker

RUN cd /opt/worker && \
  cmd=$(basename *.tgz .tgz) && \
  tar xf $cmd.tgz

RUN cd /opt/worker && \
  cmd=$(basename *.tgz .tgz) && \
  echo "#!/bin/bash -xe" > launch.sh && \
  echo printenv >> launch.sh && \
  echo "ls -lh /opt/worker" >> launch.sh && \
  echo /opt/worker/$cmd/bin/jobbrokersmokeworker -J-Xmx256m -DJOBBROKER_DB_URL='jdbc:log4jdbc:postgresql://db:5432/postgres' >> launch.sh && \
  chmod +x launch.sh

RUN chown -R wkuser:wkuser /opt/worker
USER wkuser

ENTRYPOINT ["/bin/bash", "-c", "/opt/worker/launch.sh"]