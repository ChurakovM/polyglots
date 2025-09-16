package com.polyglots.userservice.mappings

import com.polyglots.userclientapi.model.CommonUserModelWithId
import com.polyglots.userclientapi.model.GetUserResponse
import com.polyglots.userclientapi.model.PostUserRequest
import com.polyglots.userclientapi.model.PostUserResponse
import com.polyglots.userservice.repositories.dbmodels.UserDbModel
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