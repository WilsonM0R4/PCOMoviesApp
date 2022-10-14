package com.example.pcomoviesapp.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pcomoviesapp.model.User

@Entity(tableName = "USERS")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "SESSION_NAME") var sessionName: String,
    @ColumnInfo(name = "NAME") var name: String,
    @ColumnInfo(name = "PASSWORD") var password: String
)
fun UserEntity.format() = User(
    sessionName = sessionName,
    name = name,
    password = password
)