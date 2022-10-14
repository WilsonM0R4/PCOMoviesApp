package com.example.pcomoviesapp.db.dao

import androidx.room.*
import com.example.pcomoviesapp.db.entities.UserEntity

@Dao
interface UserDAO {
    @Insert
    fun insert(user:UserEntity)
    @Query("SELECT * FROM USERS WHERE USERS.SESSION_NAME = :sessionName")
    fun getInfo(sessionName:String): UserEntity
}