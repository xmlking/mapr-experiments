val reactorKotlinExtensions by project
val maprVersion by project
val maprKafkaVersion by project
val springKafkaVersion by project

apply {
    plugin("org.springframework.boot")
    plugin("org.jetbrains.kotlin.jvm")
    plugin("org.jetbrains.kotlin.plugin.spring")
    plugin("org.jetbrains.kotlin.plugin.noarg")
    plugin("io.spring.dependency-management")
}

//noArg {
//    annotation("com.my.Annotation")
//}

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