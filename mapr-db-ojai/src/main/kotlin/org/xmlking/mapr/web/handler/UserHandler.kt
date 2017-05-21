package org.xmlking.mapr

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.*
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.bodyToMono
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono
import java.net.URI.create

@Component
class UserHandler(val repository: UserRepository,
                  val errorHandler: ErrorHandler) {

    fun list(req: ServerRequest) = ok().json().body(repository.list())

    fun get0(request: ServerRequest) = {
        val resource = request.pathVariable("userId")
        repository.findOne(resource)
                .map { ok().json().body(Mono.just(it)) }
                .defaultIfEmpty(status(HttpStatus.NOT_FOUND).body(errorHandler.notFound(resource)))
    }

    fun get(request: ServerRequest): Mono<ServerResponse> {
        val resource = request.pathVariable("userId")
        return Mono.just(resource)
                .flatMap { repository.findOne(it) }
                .flatMap { ok().json().body(Mono.just(it)) }
                .switchIfEmpty(status(HttpStatus.NOT_FOUND).body(errorHandler.notFound(resource)))
    }


    fun create(request: ServerRequest) = request.bodyToMono<User>()
            .flatMap { repository.create(it) }
            .flatMap { ok().json().body(Mono.just(it)) }

    fun update(request: ServerRequest) = request.bodyToMono<User>()
            .flatMap { repository.create(it) }
            .flatMap { ok().json().body(Mono.just(it)) }
            .switchIfEmpty(status(HttpStatus.NOT_FOUND).body(errorHandler.notFound("User Not Found")))

    fun delete0(request: ServerRequest) = {
        val resource = request.pathVariable("userId")
        repository.delete(resource)
                .map { accepted().json().body(Mono.just(it)) }
                .defaultIfEmpty(status(HttpStatus.NOT_FOUND).body(errorHandler.notFound(resource)))
    }

    fun delete(request: ServerRequest) = accepted()
            .json()
            .body(repository.delete(request.pathVariable("userId")))

}

data class Error(val message: String)

@Component
class ErrorHandler() {
    fun notFound(resource: String) = Mono.just(Error("The following resource could not be found: $resource"))
}