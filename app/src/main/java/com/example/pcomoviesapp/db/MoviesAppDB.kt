package com.example.pcomoviesapp.db


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
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