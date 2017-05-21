val kotlinVersion by project
val springBootVersion by project
val reactorKotlinExtensions by project
val springDependencyManagement by project
val maprVersion by project
val maprKafkaVersion by project
val springKafkaVersion by project

buildscript {
    val kotlinVersion = "1.1.2-2"
    val springBootVersion = "2.0.0.M1"

    repositories {
        mavenCentral()
        maven { setUrl("https://repo.spring.io/milestone") }
    }
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:$springBootVersion")
    }
}

apply {
    plugin("org.springframework.boot")
}

plugins {
    val kotlinVersion = "1.1.2-2"
    val springDependencyManagement = "1.0.2.RELEASE"

    id("org.jetbrains.kotlin.jvm") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.noarg") version kotlinVersion
    id("io.spring.dependency-management") version springDependencyManagement
}

noArg {
    annotation("com.my.Annotation")
}

dependencies {
    compile(project(":mapr-commons"))
    compile(kotlinModule("stdlib-jre8"))
    compile(kotlinModule("reflect"))

    compile("org.springframework.boot:spring-boot-starter-webflux") {
        exclude(module = "hibernate-validator")
    }
    compileOnly("org.springframework:spring-context-indexer")
    compile("org.springframework.boot:spring-boot-starter-thymeleaf")
    compile("org.webjars:webjars-locator:0.32")
    compile("org.webjars:sockjs-client:1.1.2")
    compile("org.webjars:stomp-websocket:2.3.3")
    compile("org.webjars:bootstrap:4.0.0-alpha.6-1")
    compile("org.webjars:jquery:3.2.0")

//    compileOnly("com.mapr.streams:mapr-streams:$maprVersion")
//    compile("org.apache.kafka:kafka-clients:$maprKafkaVersion")
    compile("org.springframework.kafka:spring-kafka")
    testCompile("org.springframework.boot:spring-boot-starter-test")

    compile("io.projectreactor:reactor-kotlin-extensions:$reactorKotlinExtensions")
    testCompile("io.projectreactor.addons:reactor-test")

    compile("com.fasterxml.jackson.module:jackson-module-kotlin")
    compile("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
}

configurations.all {
    exclude("log4j","log4j")
    exclude("org.slf4j","slf4j-log4j12")
}