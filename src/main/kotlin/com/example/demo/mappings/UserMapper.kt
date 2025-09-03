package com.example.demo.mappings

import com.example.demo.models.GetUserResponse
import com.example.demo.models.PostUserRequest
import com.example.demo.models.PostUserResponse
import com.example.demo.repositories.dbmodels.UserDbModel
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import java.util.UUID

@Mapper(componentModel = "spring")
interface UserMapper {

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    fun postUserRequestToUserDbModel(postUserRequest: PostUserRequest): UserDbModel

    fun userDbModelToPostUserResponse(userDbModel: UserDbModel): PostUserResponse

    fun userDbModelToGetUserResponse(userDbModel: UserDbModel): GetUserResponse
}