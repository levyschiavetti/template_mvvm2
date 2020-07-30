package com.example.poc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<QuoteViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initObservers()
        viewModel.fetchQuotes()
    }

    private fun initObservers() {
        viewModel.getQuoteObservable().observe(this, Observer { list ->
            list?.forEach { quote ->
                println("Quote is from ${quote.author}")
            }
        })
    }
}