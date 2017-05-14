package org.xmlking.mapr.web

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

import org.xmlking.mapr.repository.UserRepository
import org.xmlking.mapr.model.User

@RestController
class UserController(val repository: UserRepository,
                     val env: Environment) {

    @GetMapping("/users")
    fun getUsers() = repository.list()

    @GetMapping("/user/{userId}")
    fun getUser(@PathVariable userId: String): ResponseEntity<*> {

        println("userId ********" + userId)
        val user = repository.get(userId) ?: return ResponseEntity("No User found for ID " + userId, HttpStatus.NOT_FOUND)

        return ResponseEntity(user, HttpStatus.OK)
    }

    @PostMapping(value = "/createuser")
    fun createUser(@RequestBody user: User): ResponseEntity<*> {

        repository.create(user)

        return ResponseEntity(user, HttpStatus.OK)
    }

    @DeleteMapping("/deleteuser/{userId}")
    fun deleteCustomer(@PathVariable userId: String): ResponseEntity<*> {

        if (null == repository.delete(userId)) {
            return ResponseEntity("No User found for ID " + userId, HttpStatus.NOT_FOUND)
        }

        return ResponseEntity(userId, HttpStatus.OK)

    }

    @PutMapping("/updateuser/{userId}")
    fun updateCustomer(@PathVariable userId: String, @RequestBody user: User): ResponseEntity<*> {

        val status = repository.update(user)

        return ResponseEntity(status, HttpStatus.OK)
    }
}