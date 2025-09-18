package com.polyglots.userservice.mappings

import com.polyglots.userservice.repositories.dbmodels.UserDbModel
import com.polyglots.userserviceapi.model.UserDetailsModel
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface UserServiceApiMapper {


    fun userDbModelToUserDetailsModel(userDbModel: UserDbModel): UserDetailsModel
}