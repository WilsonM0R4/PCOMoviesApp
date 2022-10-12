package com.example.pcomoviesapp.db.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import androidx.annotation.NonNull

@Entity(tableName = "USERS")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "SESSION_NAME") var sessionName: String,
    @ColumnInfo(name = "NAME") var name: String,
    @ColumnInfo(name = "PASSWORD") var password: String
)