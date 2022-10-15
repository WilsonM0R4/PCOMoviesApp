package com.example.pcomoviesapp.network

import com.example.pcomoviesapp.model.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiInterface {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): Call<ApiResponse>
}