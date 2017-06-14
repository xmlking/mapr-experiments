import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.dsl.SpringBootExtension
import org.springframework.boot.gradle.tasks.run.BootRun

buildscript {
    val springBootVersion = "2.0.0.M1"

    repositories {
        mavenCentral()
        maven { setUrl("https://repo.spring.io/milestone") }
    }
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:$springBootVersion")
    }
}

plugins {
    val kotlinVersion = "1.1.2-2"
    val springDependencyManagement = "1.0.3.RELEASE"
    val springBootVersion = "2.0.0.RELEASE"
    val dockerPluginVersion = "0.13.0"

    id("org.jetbrains.kotlin.jvm") version kotlinVersion apply false
    id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion apply false
    id("io.spring.dependency-management") version springDependencyManagement apply false
//    id("org.springframework.boot") version springBootVersion apply false TODO
    id("org.jetbrains.kotlin.plugin.noarg") version kotlinVersion // apply false TODO
    id("org.jetbrains.kotlin.plugin.jpa") version kotlinVersion apply false
    id("com.palantir.docker") version dockerPluginVersion apply false
}

subprojects {

    apply {
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.plugin.spring")
        plugin("org.jetbrains.kotlin.plugin.noarg")
        plugin("org.jetbrains.kotlin.plugin.jpa")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
    }

    repositories {
        mavenCentral()
        maven { setUrl("https://repo.spring.io/milestone") }
        maven { setUrl("http://repository.mapr.com/nexus/content/groups/mapr-public/") }
        maven { setUrl("http://repository.mapr.com/maven/") }
    }

    dependencies {
        compile(kotlinModule("stdlib-jre8"))
        compile(kotlinModule("reflect"))

        compile("org.springframework.boot:spring-boot-starter-webflux")
        compileOnly("org.springframework:spring-context-indexer")
        compile("org.springframework.boot:spring-boot-devtools")
        compile("org.springframework.boot:spring-boot-starter-actuator")
        testCompile("org.springframework.boot:spring-boot-starter-test")
    }

    tasks.withType<KotlinCompile>().all {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    noArg {
        annotation("org.xmlking.mapr.Mapr")
    }

    tasks.withType<BootRun> {
        // Ensures IntelliJ can live reload resource files
        val sourceSets = the<JavaPluginConvention>().sourceSets
        sourceResources(sourceSets["main"])
    }

    configure<SpringBootExtension> {
        buildInfo()
    }

}