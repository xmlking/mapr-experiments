MapR DB OJAI
============
This is spring boot restful webservice project to demo CRUD operations on MaprDB Json.

### Run
> use `./gradlew` instead of `gradle` if you didn't installed `gradle`
```bash
gradle mapr-db-ojai:bootRun
# run with `dev` profile. loads application-dev.properties
SPRING_PROFILES_ACTIVE=dev gradle mapr-db-ojai:bootRun
```
### Test
```bash
gradle mapr-db-ojai:test
```
### Build
```bash
gradle mapr-db-ojai:build
# build without running tests
gradle mapr-db-ojai:build -x test
# build docker image
gradle mapr-db-ojai:docker -x test
```

### Docker local Run
```bash
docker run -it -e MAPR_CLUSTER=cluster1 -e MAPR_CLDB_HOSTS=192.168.56.101 -e MAPR_CONTAINER_USER=mapr mapr/mapr-db-ojai:0.1.0-SNAPSHOT
docker run -it --cap-add SYS_ADMIN --cap-add SYS_RESOURCE --device /dev/fuse -e MAPR_CLUSTER=cluster1 -e MAPR_CLDB_HOSTS=192.168.56.101 -e MAPR_CONTAINER_USER=mapr -e MAPR_MOUNT_PATH=/mapr mapr/mapr-db-ojai:0.1.0-SNAPSHOT

# $MAPR_MOUNT_PATH/$MAPR_CLUSTER directory  will be created.
```

### API
1. curl "http://localhost:8082/users"

2. curl -H "Content-Type: application/json" -X POST -d '{"_id":"002","first_name":"kiran","last_name":"kumar","age":36}' "http://localhost:8082/createuser/"
  
5. curl -H "Content-Type: application/json" -X PUT -d '{"_id":"002","first_name":"John","last_name":"mavatoor","age":40}' "http://localhost:8082/updateuser/002"

4. curl -X DELETE "http://localhost:8082/deleteuser/002"


### Reference

* https://mapr.com/blog/getting-started-mapr-client-container/
* https://github.com/mapr-demos/mapr-pacc-sample
* http://maprdocs.mapr.com/home/AdvancedInstallation/RunningtheMapRPACC.html