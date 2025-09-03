package com.example.demo.mappings

import com.example.demo.repositories.dbmodels.UserDbModel
import com.example.model.GetUserResponse
import com.example.model.PostUserRequest
import com.example.model.PostUserResponse
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import java.util.UUID

@Mapper(componentModel = "spring")
interface UserMapper {

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    fun postUserRequestToUserDbModel(postUserRequest: PostUserRequest): UserDbModel

    fun userDbModelToPostUserResponse(userDbModel: UserDbModel): PostUserResponse

    fun userDbModelToGetUserResponse(userDbModel: UserDbModel): GetUserResponse
}