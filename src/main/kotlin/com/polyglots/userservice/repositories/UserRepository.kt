package com.polyglots.userservice.repositories

import com.polyglots.userservice.repositories.dbmodels.UserDbModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepository: JpaRepository<UserDbModel, UUID>