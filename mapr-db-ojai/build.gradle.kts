val reactorKotlinExtensions by project
val maprVersion by project
val maprOjaiVersion by project

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
    compile("com.mapr.db:maprdb:$maprVersion")
    compile("org.ojai:ojai:$maprOjaiVersion")
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