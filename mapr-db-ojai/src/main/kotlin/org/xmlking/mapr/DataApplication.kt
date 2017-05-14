package org.xmlking.mapr

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class DataApplication

fun main(args: Array<String>) {
    SpringApplication.run(DataApplication::class.java, *args)
}
