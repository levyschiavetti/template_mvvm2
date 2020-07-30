package com.example.poc

import retrofit2.Call
import retrofit2.http.GET

internal interface QuoteApi {

    @GET("quotes")
    fun fetchQuotes(): Call<List<Quote>>
}