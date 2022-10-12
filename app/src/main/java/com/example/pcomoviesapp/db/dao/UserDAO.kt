package com.example.pcomoviesapp.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.pcomoviesapp.db.entities.UserEntity

@Dao
interface UserDAO {
    @Insert
    fun insert(user:UserEntity)
    @Query("SELECT * FROM USERS WHERE USERS.SESSION_NAME = :sessionName")
    fun getInfo(sessionName:String)
}