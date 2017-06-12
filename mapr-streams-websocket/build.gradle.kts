import com.palantir.gradle.docker.DockerExtension
import org.gradle.jvm.tasks.Jar

val reactorKotlinExtensions by project
val maprVersion by project
val maprKafkaVersion by project
val springKafkaVersion by project

apply {
    plugin("com.palantir.docker")
}

val jar: Jar by tasks
docker {
    name = "${group}/${jar.baseName}:${jar.version}"
    files(jar.outputs, file("src/main/docker/ssl_truststore"))
    setDockerfile(file("src/main/docker/Dockerfile"))
    buildArgs(mapOf(
            "JAR_NAME" to jar.archiveName,
            "PORT"   to  "8080",
            "JAVA_OPTS" to "-Xms512m -Xmx1024m"
    ))
    pull(true)
    dependsOn(tasks.findByName("build"))
}

dependencies {
    compile(project(":shared"))

    compile("org.springframework.boot:spring-boot-starter-thymeleaf")
    compile("org.webjars:webjars-locator:0.32")
    compile("org.webjars:sockjs-client:1.1.2")
    compile("org.webjars:stomp-websocket:2.3.3")
    compile("org.webjars:bootstrap:4.0.0-alpha.6-1")
    compile("org.webjars:jquery:3.2.0")

//    compileOnly("com.mapr.streams:mapr-streams:$maprVersion")
//    compile("org.apache.kafka:kafka-clients:$maprKafkaVersion")
    compile("org.springframework.kafka:spring-kafka")

    compile("io.projectreactor:reactor-kotlin-extensions:$reactorKotlinExtensions")
    testCompile("io.projectreactor.addons:reactor-test")

    compile("com.fasterxml.jackson.module:jackson-module-kotlin")
    compile("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
}

configurations.all {
    exclude("log4j","log4j")
    exclude("org.slf4j","slf4j-log4j12")
}

/**
 * Configures the [docker][DockerExtension] project extension.
 */
val Project.docker get() = extensions.getByName("docker") as DockerExtension

fun Project.docker(configure: DockerExtension.() -> Unit): Unit = extensions.configure("docker", configure)