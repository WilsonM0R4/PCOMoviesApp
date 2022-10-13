package com.example.pcomoviesapp.network

import com.example.pcomoviesapp.model.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiInterface {
    @GET("movie/latest")
    suspend fun getLatestMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<ApiResponse>
}