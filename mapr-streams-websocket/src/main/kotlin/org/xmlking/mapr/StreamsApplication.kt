package org.xmlking.mapr

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.stereotype.Controller






@EnableKafka
@SpringBootApplication
class StreamsApplication


@Controller
class StaticController {

    @RequestMapping("/")
    fun index(): String {
        return "index"
    }

}
@RestController
class Producer(@Value("\${spring.kafka.template.default-topic}") private val topic: String,
               private val template: KafkaTemplate<String, String>) {

    @GetMapping(value = "/send")
    fun send(@RequestParam("key") key: String, @RequestParam("message") message: String) {
        println("Producer:  $key : $message")
        template.send(topic, key, message)
    }
}

@Component
class Consumer(@Value("\${spring.kafka.template.default-topic}") private val topic: String) {

    @KafkaListener(id = "consumerTest", topics = arrayOf("test"))
    fun consume(@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) key: String, @Payload message: String) {
        println("Consumer:  $key : $message")
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(StreamsApplication::class.java, *args)
}
