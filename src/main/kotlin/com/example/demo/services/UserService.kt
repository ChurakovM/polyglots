package com.example.demo.services

import com.example.demo.mappings.UserMapper
import com.example.demo.models.GetMultipleUsersResponse
import com.example.demo.models.GetUserResponse
import com.example.demo.models.PostUserRequest
import com.example.demo.models.PostUserResponse
import com.example.demo.repositories.UserRepository
import com.example.demo.repositories.dbmodels.UserDbModel
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper) {

    fun createUser(postUserRequest: PostUserRequest): PostUserResponse {
        val userDbModel = userMapper.postUserRequestToUserDbModel(postUserRequest);
        userRepository.save(userDbModel)
        return userMapper.userDbModelToPostUserResponse(userDbModel)
    }

    fun getUserById(userId: UUID): GetUserResponse {
        val userDbModel = userRepository.findById(userId).get()
        return userMapper.userDbModelToGetUserResponse(userDbModel)
    }

    fun getMultipleUsers(): GetMultipleUsersResponse {
        val foundUsers: List<UserDbModel> = userRepository.findAll()
        return GetMultipleUsersResponse(foundUsers)
    }

    fun removeUserById(userId: UUID) = userRepository.deleteById(userId)


}