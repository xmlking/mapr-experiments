MapR Streams WebSocket
======================

### Setup
Setup for MapR Streams 
```bash
# from MapR cluster/sandbox node terminal, Create a new Stream and Topic in your MapR Cluster:
# ssh mapr@192.68.56.101
maprcli stream create -path /user/mapr/streams/my_stream -produceperm p -consumeperm p -topicperm p
maprcli stream topic create -path /user/mapr/streams/my_stream -topic test -partitions 1
# to get info on the ekgs topic :
maprcli stream topic info -path /user/mapr/streams/my_stream -topic test
# to delete
maprcli stream topic delete -path /user/mapr/streams/my_stream -topic test 
```
Setup for Apache Kafka 
```bash
# Start Zookeeper
bin/zookeeper-server-start ./etc/kafka/zookeeper.properties
# Start Kafka
bin/kafka-server-start ./etc/kafka/server.properties
```

### Run
> use `./gradlew` instead of `gradle` if you didn't installed `gradle`
> start kafka http://docs.confluent.io/current/quickstart.html
```bash
gradle mapr-streams-websocket:bootRun
# run with `mapr` profile. loads application-mapr.yml
SPRING_PROFILES_ACTIVE=mapr gradle mapr-streams-websocket:bootRun
```

### Test
```bash
gradle mapr-streams-websocket:test
```

### Build
```bash
gradle mapr-streams-websocket:build
# build without running tests
gradle mapr-streams-websocket:build -x test
# build docker image
gradle mapr-streams-websocket:docker
```

### Docker local Run
```bash
# Non-Secure Cluster without FUSE-Based POSIX Client
 docker run -it -p 8081:8080 \
       -e MAPR_CLUSTER=cluster1 \
       -e MAPR_CLDB_HOSTS=192.168.56.101 \
       -e MAPR_CONTAINER_USER=mapr \
       -e MAPR_CONTAINER_UID=2000 \
       -e MAPR_CONTAINER_GID=2000 \
       -e MAPR_CONTAINER_GROUP=mapr \
        mapr/mapr-streams-websocket:0.1.0-SNAPSHOT
```

### API
> send message 
http://localhost:8081/send?key=myKey&message=myMessage

### Reference

* https://mapr.com/blog/getting-started-mapr-client-container/
* https://github.com/mapr-demos/mapr-pacc-sample
* http://maprdocs.mapr.com/home/AdvancedInstallation/RunningtheMapRPACC.html
* https://github.com/kirankumar-mahi/mapr-streams-example
* https://github.com/caroljmcdonald/live-mapr-streaming-websocket/tree/master