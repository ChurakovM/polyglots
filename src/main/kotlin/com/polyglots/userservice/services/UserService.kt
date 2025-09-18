package com.polyglots.userservice.services

import com.polyglots.userclientapi.model.GetMultipleUsersResponse
import com.polyglots.userclientapi.model.GetUserResponse
import com.polyglots.userclientapi.model.PostUserRequest
import com.polyglots.userclientapi.model.PostUserResponse
import com.polyglots.userservice.mappings.UserClientApiMapper
import com.polyglots.userservice.mappings.UserServiceApiMapper
import com.polyglots.userservice.repositories.UserRepository
import com.polyglots.userservice.repositories.dbmodels.UserDbModel
import com.polyglots.userserviceapi.model.LoginRequest
import com.polyglots.userserviceapi.model.UserDetailsModel
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
    private val userClientApiMapper: UserClientApiMapper,
    private val userServiceApiMapper: UserServiceApiMapper,
    private val passwordEncoder: PasswordEncoder
) {

    fun createUser(postUserRequest: PostUserRequest): PostUserResponse {
        val userDbModel = userClientApiMapper.postUserRequestToUserDbModel(postUserRequest);
        val hashedPassword = passwordEncoder.encode(postUserRequest.password)
        userDbModel.password = hashedPassword
        userRepository.save(userDbModel)
        return userClientApiMapper.userDbModelToPostUserResponse(userDbModel)
    }

    fun getUserById(userId: UUID): GetUserResponse {
        val userDbModel = userRepository.findById(userId).get()
        return userClientApiMapper.userDbModelToGetUserResponse(userDbModel)
    }

    fun getMultipleUsers(): GetMultipleUsersResponse {
        val foundUsers: List<UserDbModel> = userRepository.findAll()
        val mappedUsers = userClientApiMapper.listOfUserDbModelToListOfCommonUserModelWithId(foundUsers)
        return GetMultipleUsersResponse(mappedUsers)
    }

    fun removeUserById(userId: UUID) = userRepository.deleteById(userId)

    fun verifyCredentials(loginRequest: LoginRequest): UserDetailsModel? {
        val username = loginRequest.username
        val rawPassword = loginRequest.password
        val user = userRepository.findByUsername(username) ?: return null
        return if (passwordEncoder.matches(rawPassword, user.password))
            userServiceApiMapper.userDbModelToUserDetailsModel(user) else null
    }
}