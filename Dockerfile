FROM confluentinc/cp-kafka-connect:3.2.0

WORKDIR /kafka-connectproject
COPY config config
COPY target target

VOLUME /kafka-connectProject/config
VOLUME /kafka-connectProject/offsets

CMD CLASSPATH="$(find target/ -type f -name '*.jar'| grep '\-package' | tr '\n' ':')" connect-standalone config/worker.properties config/MySourceConnector.properties