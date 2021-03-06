FROM java:openjdk-8-jre

MAINTAINER Tom Van den Bulck (tomvdbulck@gmail.com)

#Based on https://github.com/spotify/docker-kafka

ENV DEBIAN_FRONTEND=noninteractive

ENV KAFKA_VERSION=0.11.0.1
ENV SCALA_VERSION=2.11
ENV KAFKA_HOME /opt/kafka_"$SCALA_VERSION"-"$KAFKA_VERSION"


# Install Kafka, Zookeeper and other needed things
RUN apt-get update && \
    apt-get install -y wget supervisor dnsutils && \
    rm -rf /var/lib/apt/lists/* && \
    apt-get clean && \
    wget -q http://www-eu.apache.org/dist/kafka/"$KAFKA_VERSION"/kafka_"$SCALA_VERSION"-"$KAFKA_VERSION".tgz -O /tmp/kafka_"$SCALA_VERSION"-"$KAFKA_VERSION".tgz && \
    tar xfz /tmp/kafka_"$SCALA_VERSION"-"$KAFKA_VERSION".tgz -C /opt && \
    rm /tmp/kafka_"$SCALA_VERSION"-"$KAFKA_VERSION".tgz


ADD scripts/start-zookeeper.sh /usr/bin/start-zookeeper.sh
RUN chmod +x /usr/bin/start-zookeeper.sh

ADD scripts/start-kafka.sh /usr/bin/start-kafka.sh
RUN chmod +x /usr/bin/start-kafka.sh


# Supervisor config
ADD supervisor/zookeeper.conf supervisor/kafka.conf /etc/supervisor/conf.d/


# 2181 is zookeeper, 9092 is kafka
EXPOSE 2181 9092

CMD ["supervisord", "-n"]


