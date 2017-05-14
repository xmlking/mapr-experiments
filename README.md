MapR Experiments
================
This demo application showcases usage of **MapR OJAI API**, **MapR-Streams** to build microservices using the following technology stack:
* Spring Framework 5
* Spring Boot 2.0.0
* Spring WebFlux
* Multi-project builds with Gradle Kotlin Script. 

Highlights of this application are:
* Use of Server-Sent Events (SSE) rendered in HTML by Thymeleaf from a reactive data stream.
* Use of Server-Sent Events (SSE) rendered in JSON by Spring WebFlux from a reactive data stream. 
* Use of Thymeleaf's fully-HTML5-compatible syntax.
* Use of `webjars` for client-side dependency managements. 

### Running
```bash
./gradlew :mapr-db-ojai:bootRun
```
### Testing
```bash
./gradlew :mapr-db-ojai:test
```
### Building 
```bash
./gradlew :mapr-db-ojai:build
```

### Gradle Commands
```bash
# upgrade project gradle version
gradle wrapper --gradle-version 3.5
# gradle daemon status 
gradle --status
gradle --stop
```
