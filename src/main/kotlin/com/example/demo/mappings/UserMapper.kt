package com.example.demo.mappings

import com.example.demo.repositories.dbmodels.UserDbModel
import com.example.generated.models.CommonUserModelWithId
import com.example.generated.models.GetUserResponse
import com.example.generated.models.PostUserRequest
import com.example.generated.models.PostUserResponse
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface UserMapper {

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    fun postUserRequestToUserDbModel(postUserRequest: PostUserRequest): UserDbModel

    fun userDbModelToPostUserResponse(userDbModel: UserDbModel): PostUserResponse

    fun userDbModelToGetUserResponse(userDbModel: UserDbModel): GetUserResponse

    fun listOfUserDbModelToListOfCommonUserModelWithId(foundUsers: List<UserDbModel>): List<CommonUserModelWithId>
}