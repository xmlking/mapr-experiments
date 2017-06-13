// We can use either @RestController or new Functional Handler
/**
package org.xmlking.mapr.web

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.xmlking.mapr.User
import org.xmlking.mapr.UserRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


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
**/