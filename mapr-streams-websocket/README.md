MapR Streams WebSocket
======================

### Setup
Install and fire up the Sandbox using the instructions here: http://maprdocs.mapr.com/home/SandboxHadoop/c_sandbox_overview.html. 
 
### Run
> use `./gradlew` instead of `gradle` if you didn't installed `gradle`
> start kafka http://docs.confluent.io/current/quickstart.html
```bash
gradle mapr-streams-websocket:bootRun
# run with `dev` profile. loads application-dev.properties
SPRING_PROFILES_ACTIVE=dev gradle mapr-streams-websocket:bootRun
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
docker run -it -e MAPR_CLUSTER=cluster1 -e MAPR_CLDB_HOSTS=192.168.56.101 -e MAPR_CONTAINER_USER=mapr mapr/mapr-streams-websocket:0.1.0-SNAPSHOT
docker run -it --cap-add SYS_ADMIN --cap-add SYS_RESOURCE --device /dev/fuse -e MAPR_CLUSTER=cluster1 -e MAPR_CLDB_HOSTS=192.168.56.101 -e MAPR_CONTAINER_USER=mapr -e MAPR_MOUNT_PATH=/mapr mapr/mapr-streams-websocket:0.1.0-SNAPSHOT

# $MAPR_MOUNT_PATH/$MAPR_CLUSTER directory  will be created.
```

### API
> send message 
http://localhost:8081/send?key=myKey&message=myMessage