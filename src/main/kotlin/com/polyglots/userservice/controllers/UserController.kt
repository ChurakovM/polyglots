package com.polyglots.userservice.controllers

import com.polyglots.userservice.services.UserService
import com.example.generated.apis.DefaultApi
import com.example.generated.models.GetMultipleUsersResponse
import com.example.generated.models.GetUserResponse
import com.example.generated.models.PostUserRequest
import com.example.generated.models.PostUserResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class UserController(private val userService: UserService): DefaultApi {

    override fun usersPost(postUserRequest: PostUserRequest): ResponseEntity<PostUserResponse> {
        val response = userService.createUser(postUserRequest)
        return ResponseEntity<PostUserResponse>(response, HttpStatus.CREATED)
    }

    override fun usersUserIdGet(userId: String): ResponseEntity<GetUserResponse> {
        val userUuId = UUID.fromString(userId)
        val response = userService.getUserById(userUuId)
        return ResponseEntity<GetUserResponse>(response, HttpStatus.OK)
    }

    override fun usersGet(): ResponseEntity<GetMultipleUsersResponse> {
        val response = userService.getMultipleUsers()
        return ResponseEntity<GetMultipleUsersResponse>(response, HttpStatus.OK)
    }

    override fun usersUserIdDelete(userId: String): ResponseEntity<Unit> {
        val userUuId = UUID.fromString(userId)
        userService.removeUserById(userUuId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}