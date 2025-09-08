package com.example.demo.services

import com.example.demo.mappings.UserMapper
import com.example.demo.repositories.UserRepository
import com.example.demo.repositories.dbmodels.UserDbModel
import com.example.generated.models.GetMultipleUsersResponse
import com.example.generated.models.GetUserResponse
import com.example.generated.models.PostUserRequest
import com.example.generated.models.PostUserResponse
import org.springframework.stereotype.Service
import java.util.*

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
        val mappedUsers = userMapper.listOfUserDbModelToListOfCommonUserModelWithId(foundUsers)
        return GetMultipleUsersResponse(mappedUsers)
    }

    fun removeUserById(userId: UUID) = userRepository.deleteById(userId)


}