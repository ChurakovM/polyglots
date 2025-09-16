package com.polyglots.userservice.services

import com.polyglots.userclientapi.model.GetMultipleUsersResponse
import com.polyglots.userclientapi.model.GetUserResponse
import com.polyglots.userclientapi.model.PostUserRequest
import com.polyglots.userclientapi.model.PostUserResponse
import com.polyglots.userservice.mappings.UserMapper
import com.polyglots.userservice.repositories.UserRepository
import com.polyglots.userservice.repositories.dbmodels.UserDbModel
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper,
    private val passwordEncoder: PasswordEncoder) {

    fun createUser(postUserRequest: PostUserRequest): PostUserResponse {
        val userDbModel = userMapper.postUserRequestToUserDbModel(postUserRequest);
        val hashedPassword = passwordEncoder.encode(postUserRequest.password)
        userDbModel.password = hashedPassword
        userRepository.save(userDbModel)
        return userMapper.userDbModelToPostUserResponse(userDbModel)
    }

    fun getUserById(userId: UUID): GetUserResponse {
        val userDbModel = userRepository.findById(userId).get()
        return userMapper.userDbModelToGetUserResponse(userDbModel)
    }

    fun getMultipleUsers(): GetMultipleUsersResponse {
        val foundUsers: List<UserDbModel> = userRepository.findAll()
        val mappedUsers = userMapper.listOfUserDbModelToListOfCommonUserModelWithId(foundUsers)
        return GetMultipleUsersResponse(mappedUsers)
    }

    fun removeUserById(userId: UUID) = userRepository.deleteById(userId)


}