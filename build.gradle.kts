import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val version by project
val kotlinVersion by project

buildscript {
    repositories {
        gradleScriptKotlin()
    }
    dependencies {
        classpath(kotlinModule("gradle-plugin"))

    }
}

plugins {
    base
}

apply {
    plugin("idea")
}

allprojects {
    group = "org.xmlking.mapr"
    version = version

    repositories {
        gradleScriptKotlin()
        mavenCentral()
        maven { setUrl("https://repo.spring.io/milestone") }
        maven { setUrl("http://repository.mapr.com/nexus/content/groups/mapr-public/") }
        maven { setUrl("http://repository.mapr.com/maven/") }
    }
}

// Configure all KotlinCompile tasks on each sub-project
subprojects {

    tasks.withType<KotlinCompile> {
        println("Configuring $name in project ${project.name}...")
        sourceCompatibility = "1.8" // JavaVersion.VERSION_1_8
        targetCompatibility = "1.8"
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

dependencies {
    // Make the root project archives configuration depend on every subproject
    subprojects.forEach {
        archives(it)
    }
}