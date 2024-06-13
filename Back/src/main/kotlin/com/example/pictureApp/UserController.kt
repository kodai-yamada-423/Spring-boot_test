package com.example.pictureApp

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/users")
class UserController(@Autowired val userRepository: UserRepository) {

    @GetMapping
    fun getAllUsers(): List<User> {
       return userRepository.findAll()
    }

    @GetMapping("/api/user")
    fun getUserId(@PathVariable pass:String): ResponseEntity<User> =
        userRepository.findById(pass)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()


    @PostMapping
    fun createUser(@RequestBody user: User): ResponseEntity<User> {
        userRepository.save(user)
        return ResponseEntity.ok(user)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Void> =
        if (userRepository.deleteById(id) > 0) {
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }
}

