MapR Experiments
================
This demo application showcases usage of **MapR OJAI API**, **MapR-Streams** to build Microservices using the following technology stack:
* MapR OJAI API & Streams
* Spring Framework 5
* Spring Boot 2.0.0
* Spring WebFlux
* Gradle Kotlin Script
* MapR Persistent Application Client Containers (PACCs) for docker deployment.

##### Key Features:
* Docker containers are ephemeral by design. Containerized applications based on MapR PACC, can leverage MapR-FS as a persistent data store.
* Containerized applications can easily leverage all the MapR platform services (MapR-FS, MapR-DB, MapR Streams) securely from with in the container.
* Secure authentication at container level, allowing for secure connections.
* Multi-project builds with Gradle Kotlin Script. 
* Non-blocking Reactive Microservices with Spring 5
* Spring WebFlux and Functional Style Routes

### Prerequisites
1. MapR Sandbox [Setup Instructions](./docs/MapR.md)
2. Docker for Mac [Installation](https://docs.docker.com/docker-for-mac/install/)
3. Gradle 4 (Install via [sdkman](http://sdkman.io/))

> See [mapr-db-ojai](/mapr-db-ojai/), [mapr-streams-websocket](/mapr-streams-websocket) sub projects for more documentation. 

### Run
```bash
gradle mapr-db-ojai:bootRun
gradle mapr-streams-websocket:bootRun
# If you dont have MapR cleint installed on your development box, run on Docker Container that has MapR cleint. 
```

### Test
```bash
gradle test
```

### Build
```bash
gradle build
# skip tests.  
gradle build -x test
```

### Gradle Commands
```bash
# upgrade project gradle version
gradle wrapper --gradle-version=4.0-rc-2
# gradle daemon status 
gradle --status
gradle --stop
```

