package com.example.pcomoviesapp.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.pcomoviesapp.db.entities.MovieEntity

@Dao
interface MovieDAO {
    @Insert
    fun insert(movie:MovieEntity)
    @Query("SELECT * FROM MOVIES WHERE MOVIES.ID = :id")
    fun getInfo(id:Int) : LiveData<List<MovieEntity>>
}