package com.example.pcomoviesapp.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.pcomoviesapp.db.entities.MovieEntity

@Dao
interface MovieDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie:MovieEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movies:List<MovieEntity>)
    @Query("SELECT * FROM MOVIES WHERE MOVIES.ID = :id")
    fun getDetail(id:Int) : List<MovieEntity>
    @Query("SELECT * FROM MOVIES")
    fun getAll() : List<MovieEntity>
    @Query("SELECT * FROM MOVIES WHERE IS_FAVORITE = 1")
    fun getFavorites() : List<MovieEntity>
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(movie: MovieEntity)
}