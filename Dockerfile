FROM adoptopenjdk/openjdk11:jdk-11.0.3_7
VOLUME /tmp
ADD target/dependency/jacocoagent.jar jacocoagent.jar
ADD target/stock.jar stock.jar
ENTRYPOINT exec java $JAVA_OPTS -jar /stock.jar