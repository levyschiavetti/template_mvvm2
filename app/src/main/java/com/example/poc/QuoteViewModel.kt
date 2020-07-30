package com.example.poc

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

internal class QuoteViewModel : ViewModel() {

    private val quoteRepository = QuoteRepository()

    internal fun getQuoteObservable(): LiveData<List<Quote>?> {
        return quoteRepository.getQuoteObservable()
    }

    internal fun fetchQuotes() {
        quoteRepository.fetchQuotes()
    }
}