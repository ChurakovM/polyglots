package com.example.demo.repositories

import com.example.demo.repositories.dbmodels.UserDbModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepository: JpaRepository<UserDbModel, UUID>