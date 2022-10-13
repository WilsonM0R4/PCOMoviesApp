package com.example.pcomoviesapp.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("status_code") var statusCode: Int = 0,
    @SerializedName("status_message") var statusMessage: String = "",
    @SerializedName("page") var page: Int = 0,
    @SerializedName("results") var results: List<MovieItem> = listOf(),
    @SerializedName("total_pages") var totalPages: Int = 0,
    @SerializedName("total_results") var totalResults: Int = 0
)
