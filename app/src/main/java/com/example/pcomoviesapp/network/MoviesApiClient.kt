package com.example.pcomoviesapp.network

import android.content.Context
import okhttp3.OkHttpClient
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.pcomoviesapp.model.ApiResponse
import com.example.pcomoviesapp.model.format
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MoviesApiClient {

    private val imageMainPath = "https://image.tmdb.org/t/p/w500"
    private val apiMainPath = "https://api.themoviedb.org/3/"
    private val apiKey = "09963e300150f9831c46a1828a82a984"

    private fun getClient(context: Context): Retrofit {
        val okHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(
                ChuckerInterceptor.Builder(context = context)
                    .collector(ChuckerCollector(context))
                    .maxContentLength(250000L)
                    .redactHeaders(emptySet())
                    .alwaysReadResponseBody(false)
                    .build()
            )
        }.build()
        return Retrofit.Builder().baseUrl(apiMainPath)
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
    }

    suspend fun getLatestMovies(context: Context, responseCallback: ResponseCallback) {
        getClient(context).create(MoviesApiInterface::class.java).getLatestMovies(apiKey, "en-US")
            .enqueue(
                object : Callback<ApiResponse> {
                    override fun onResponse(
                        call: Call<ApiResponse>,
                        response: Response<ApiResponse>
                    ) {
                        response.body()?.let {
                            responseCallback.onSuccess(it)
                        }
                    }

                    override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                        responseCallback.onFail(t)
                    }
                }
            )
    }
}