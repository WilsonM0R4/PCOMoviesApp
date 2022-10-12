package com.example.pcomoviesapp.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.pcomoviesapp.db.dao.MovieDAO
import com.example.pcomoviesapp.db.dao.UserDAO
import com.example.pcomoviesapp.db.entities.MovieEntity
import com.example.pcomoviesapp.db.entities.UserEntity

@Database(entities = [MovieEntity::class, UserEntity::class], version = 1)
abstract class MoviesAppDB : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: MoviesAppDB? = null
        fun getInstance(context: Context): MoviesAppDB {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MoviesAppDB::class.java,
                        "MOVIES_DB"
                    )
                        .build()
                }
            }
            return INSTANCE!!
        }
    }

    abstract fun moviesDAO(): MovieDAO
    abstract fun userDAO(): UserDAO
}