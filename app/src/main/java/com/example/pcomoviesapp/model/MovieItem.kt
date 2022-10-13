package com.example.pcomoviesapp.model

import com.example.pcomoviesapp.db.entities.MovieEntity
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieItem(
    @SerializedName("id") var id: Int,
    @SerializedName("original_language") var originalLanguage: String,
    @SerializedName("original_title") var originalTitle: String,
    @SerializedName("overview") var overview: String,
    @SerializedName("popularity") var popularity: Double,
    @SerializedName("poster_path") var posterPath: String,
    @SerializedName("release_date") var releaseDate: String,
    @SerializedName("title") var title: String,
    @SerializedName("vote_average") var voteAverage: Double,
    @SerializedName("vote_count") var voteCount: Int
)
fun MovieItem.format() = Movie(
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
    isFavorite = false
)
