package com.example.demo.repositories.dbmodels

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity
data class UserDbModel(

    @Id

    var id: UUID,

    var nickname: String,

    var firstName: String,

    var secondName: String,

    var email: String,

    var phoneNumber: String
)