package com.example.pcomoviesapp.model

import com.example.pcomoviesapp.db.entities.MovieEntity
import java.io.Serializable

data class Movie(
    var id: Int = 0,
    var originalLanguage: String = "",
    var originalTitle: String = "",
    var overview: String = "",
    var popularity: Double = 0.0,
    var posterPath: String = "",
    var releaseDate: String = "",
    var title: String = "",
    var voteAverage: Double = 0.0,
    var voteCount: Int = 0,
    var isFavorite: Boolean = false
) : Serializable

fun Movie.toPersistence() = MovieEntity(
    id = id,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    title = title,
    voteAverage = voteAverage,
    voteCount = voteCount,
    isFavorite = if (isFavorite) 1 else 0
)
