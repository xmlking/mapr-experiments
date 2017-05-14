package org.xmlking.mapr.web.handler

import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.xmlking.mapr.model.*
import org.xmlking.mapr.repository.UserRepository
import org.xmlking.mapr.util.*
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono
import java.net.URI.*
import java.net.URLDecoder


@Component
class UserHandler(val repository: UserRepository,
                       val env: Environment) {

    fun getUsers(req: ServerRequest) = ok().json().body(Flux.fromIterable(repository.list()))

    fun getUser(req: ServerRequest) = ok().json().body(Mono.just(repository.get(req.pathVariable("userId")) ))

}
