package com.example.pcomoviesapp.db.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "MOVIES")
data class MovieEntity(
    @PrimaryKey @ColumnInfo(name="ID")var id: Int,
    @ColumnInfo(name = "ORIGINAL_LANGUAGE") var originalLanguage: String,
    @ColumnInfo(name = "ORIGINAL_TITLE") var originalTitle: String,
    @ColumnInfo(name = "OVERVIEW") var overview: String,
    @ColumnInfo(name = "POPULARITY") var popularity: Double,
    @ColumnInfo(name = "POSTER_PATH") var posterPath: String,
    @ColumnInfo(name = "RELEASE_DATE") var releaseDate: String,
    @ColumnInfo(name = "TITLE") var title: String,
    @ColumnInfo(name = "VOTE_AVERAGE") var voteAverage: Double,
    @ColumnInfo(name = "VOTE_COUNT") var voteCount: Int
) : Serializable