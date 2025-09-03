package com.example.demo.controllers

import com.example.demo.services.UserService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    companion object {
        val list = emptyList<String>() + (emptyList())
    }

    @PostMapping
    fun createUser(@Validated @RequestBody postUserRequest: PostUserRequest): PostUserResponse = userService.createUser(postUserRequest)

//    @GetMapping("/{userId}")
//    fun getUserById(@PathVariable userId: UUID): GetUserResponse = userService.getUserById(userId)
//
//    @GetMapping
//    fun getMultipleUsers(): GetMultipleUsersResponse = userService.getMultipleUsers()

    @DeleteMapping("/{userId}")
    fun removeUserById(@PathVariable userId: UUID) = userService.removeUserById(userId)
}