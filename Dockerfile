FROM p7hb/docker-spark


ADD ./target/TriangleCountingSpark-1.0-SNAPSHOT-jar-with-dependencies.jar /
ADD followers.txt /root/

CMD spark-submit --class Main --master local /TriangleCountingSpark-1.0-SNAPSHOT-jar-with-dependencies.jar