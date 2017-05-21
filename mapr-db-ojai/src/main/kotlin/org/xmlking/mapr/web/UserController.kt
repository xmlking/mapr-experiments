package org.xmlking.mapr.web

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body

import org.xmlking.mapr.UserRepository
import org.xmlking.mapr.User
import org.xmlking.mapr.json
import reactor.core.publisher.Mono
import org.apache.commons.lang.StringUtils.defaultIfEmpty
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PutMapping
import org.apache.commons.lang.StringUtils.defaultIfEmpty
import java.util.UUID
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import com.sun.deploy.util.SearchPath.findOne
import org.springframework.web.bind.annotation.GetMapping
import reactor.core.publisher.Flux


@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api2/users")
class UserController(val repository: UserRepository) {

    @CrossOrigin("http://localhost:4200")
    @GetMapping
    fun list() = repository.list()

    @GetMapping(path = arrayOf("/startingwith/{letter}"))
    fun findAllByFirstNameLike(
            @PathVariable("letter") letter: String): Flux<User> {
        return repository.findAllByFirstNameLike(letter)
    }

    @CrossOrigin
    @GetMapping(path = arrayOf("/{id}"))
    operator fun get(@PathVariable("id") userId: String): Mono<ResponseEntity<User>> {
        return repository.findOne(userId)
                .map { ResponseEntity(it, HttpStatus.OK) }
                .defaultIfEmpty(ResponseEntity(HttpStatus.NOT_FOUND))
    }

    @PostMapping
    fun create(@RequestBody user: User): Mono<ResponseEntity<User>> {
        return repository.create(user)
                .map({ savedUser -> ResponseEntity(savedUser, HttpStatus.CREATED) })
    }

    @DeleteMapping(path = arrayOf("/{id}"))
    fun delete(@PathVariable("id") userId: String): Mono<ResponseEntity<User>> {
        return repository.delete(userId)
                .map { ResponseEntity(it, HttpStatus.ACCEPTED) }
                .defaultIfEmpty(ResponseEntity(HttpStatus.NOT_FOUND))
    }

    @PutMapping
    fun update(@RequestBody user: User): Mono<ResponseEntity<User>> {
        return repository.update(user)
                .map({ savedUser -> ResponseEntity(savedUser, HttpStatus.CREATED) })
                .defaultIfEmpty(ResponseEntity(HttpStatus.NOT_FOUND))
    }
}