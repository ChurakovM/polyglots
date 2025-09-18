package com.polyglots.userservice.controllers

import com.polyglots.userservice.services.UserService
import com.polyglots.userserviceapi.api.DefaultApi
import com.polyglots.userserviceapi.model.LoginRequest
import com.polyglots.userserviceapi.model.UserDetailsModel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class UserServiceApiController(private val userService: UserService): DefaultApi {

    override fun serviceApiUsersVerifyCredentialsPost(loginRequest: LoginRequest): ResponseEntity<UserDetailsModel> {
        val user = userService.verifyCredentials(loginRequest)
        return if (user == null) ResponseEntity(HttpStatus.UNAUTHORIZED)
            else ResponseEntity<UserDetailsModel>(user, HttpStatus.OK)
    }
}