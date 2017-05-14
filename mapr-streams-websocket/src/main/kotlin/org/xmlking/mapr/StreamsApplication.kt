package org.xmlking.mapr

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class StreamsApplication

fun main(args: Array<String>) {
    SpringApplication.run(StreamsApplication::class.java, *args)
}
