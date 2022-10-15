package com.example.pcomoviesapp.model

data class Response(
    var statusCode: Int = 0,
    var statusMessage: String = "",
    var page: Int = 0,
    var results: List<Movie> = listOf(),
    var totalPages: Int = 0,
    var totalResults: Int = 0
)
