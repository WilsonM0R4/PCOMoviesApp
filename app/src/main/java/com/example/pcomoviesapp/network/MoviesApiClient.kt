package com.example.pcomoviesapp.network

import android.content.Context
import okhttp3.OkHttpClient
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.pcomoviesapp.common.Constants
import com.example.pcomoviesapp.model.ApiResponse
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MoviesApiClient {

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
        return Retrofit.Builder().baseUrl(Constants.API_MAIN_PATH)
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
    }

    fun getLatestMovies(context: Context, page:Int, responseCallback: ResponseCallback) {
        getClient(context).create(MoviesApiInterface::class.java).getPopularMovies(Constants.API_KEY, page)
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