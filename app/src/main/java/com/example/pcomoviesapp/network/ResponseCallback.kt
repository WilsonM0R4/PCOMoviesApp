package com.example.pcomoviesapp.network

import com.example.pcomoviesapp.model.ApiResponse

interface ResponseCallback {
    fun onSuccess(response: ApiResponse)
    fun onFail(ex:Throwable)
}