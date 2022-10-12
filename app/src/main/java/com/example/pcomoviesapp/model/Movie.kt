package com.example.pcomoviesapp.model

import com.example.pcomoviesapp.db.entities.MovieEntity

data class Movie(
    var id: Int,
    var originalLanguage: String,
    var originalTitle: String,
    var overview: String,
    var popularity: Double,
    var posterPath: String,
    var releaseDate: String,
    var title: String,
    var voteAverage: Double,
    var voteCount: Int,
    var isFavorite: Boolean
)
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
