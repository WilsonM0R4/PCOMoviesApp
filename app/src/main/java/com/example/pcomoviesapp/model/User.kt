package com.example.pcomoviesapp.model

import com.example.pcomoviesapp.db.entities.UserEntity

data class User(
    var sessionName: String = "",
    var name: String = "",
    var password: String
)
fun User.forPersistence() = UserEntity(
    sessionName = sessionName,
    name = name,
    password = password
)