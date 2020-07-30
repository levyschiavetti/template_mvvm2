package com.example.poc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

internal class QuoteRepository {

    private val quoteObservable = MutableLiveData<List<Quote>?>()


    internal fun getQuoteObservable(): LiveData<List<Quote>?> = quoteObservable

    private fun getClient() =
        OkHttpClient()
            .newBuilder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

    private fun getRetrofit() =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    internal fun fetchQuotes() {
        val request = getRetrofit().create(QuoteApi::class.java)
        request.fetchQuotes().enqueue(object : Callback<List<Quote>> {
            override fun onFailure(call: Call<List<Quote>>, t: Throwable) {
                throw Exception("onFailure Call 1")
            }

            override fun onResponse(call: Call<List<Quote>>, response: Response<List<Quote>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        quoteObservable.value = it
                    } ?: throw Exception("onFailure Parsing")
                } else {
                    throw Exception("onFailure Call 2")
                }
            }
        })
    }

    companion object {
        const val BASE_URL = "https://programming-quotes-api.herokuapp.com/"
    }
}
