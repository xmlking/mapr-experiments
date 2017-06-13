MapR DB OJAI
============
This is spring boot restful webservice project to demo CRUD operations on MapR-DB.

### Setup
```bash
# from MapR edge/cluster/sandbox node terminal, create a table using maprcli:
maprcli table create -path /user/mapr/tables/my_users
# Or from  mcs web interface, create a table
http://<sandbox_ip>:8443/mcs
# to delete table
maprcli table delete -path /user/mapr/tables/my_users
```
> copy **ssl_truststore** from MapR cluster node to project's docker folder.
```bash
scp mape@sandbox:/opt/mapr/conf/ssl_truststore mapr-db-ojai/src/main/docker/
```
> Don't check-in secure files to Git.

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
# skip tests. You may need skip test step as tests need MapR connection. 
gradle :mapr-db-ojai:build -x test
# build docker image
gradle mapr-db-ojai:docker -x test
```

### Docker local Run
```bash
docker run -it -p 8082:8080 -e MAPR_CLUSTER=cluster1 -e MAPR_CLDB_HOSTS=192.168.56.101 -e MAPR_CONTAINER_USER=mapr -e MAPR_CONTAINER_UID=2000 -e MAPR_CONTAINER_GID=2000 -e MAPR_CONTAINER_GROUP=mapr mapr/mapr-db-ojai:0.1.0-SNAPSHOT
docker run -it -p 8082:8080 \
        --cap-add SYS_ADMIN --cap-add SYS_RESOURCE --device /dev/fuse  \
       -e MAPR_CLUSTER=cluster1 \
       -e MAPR_CLDB_HOSTS=192.168.56.101 \
       -e MAPR_CONTAINER_USER=mapr \
       -e MAPR_CONTAINER_UID=2000 \
       -e MAPR_CONTAINER_GID=2000 \
       -e MAPR_CONTAINER_GROUP=mapr \
       -e MAPR_MOUNT_PATH=/mapr \
       mapr/mapr-db-ojai:0.1.0-SNAPSHOT

# $MAPR_MOUNT_PATH/$MAPR_CLUSTER directory  will be created.
```

### API
1. curl "http://localhost:8082/api/users"
2. curl -H "Content-Type: application/json" -X POST -d '{"_id":"006","firstName":"sumo6","lastName":"demo6"}' "http://localhost:8082/api/users"
3. curl -H "Content-Type: application/json" -X PUT -d '{"_id":"006","firstName":"sumo6","lastName":"demo6", "role": "STAFF", "email": "sumo6@demo.com", "age":36}' "http://localhost:8082/api/users"
4. curl "http://localhost:8082/api/users/006"
5. curl -X DELETE "http://localhost:8082/api/users/006"

### Reference

* https://mapr.com/blog/getting-started-mapr-client-container/
* https://github.com/mapr-demos/mapr-pacc-sample
* http://maprdocs.mapr.com/home/AdvancedInstallation/RunningtheMapRPACC.html