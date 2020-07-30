package com.example.poc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

internal class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<QuoteViewModel>()

    private val adapter = QuoteAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        initObservers()
        viewModel.fetchQuotes()
    }

    private fun setupRecyclerView() {
        quoteRecyclerView.adapter = adapter
    }

    private fun initObservers() {
        viewModel.getQuoteObservable().observe(this, Observer { quoteList ->
            quoteList?.let { adapter.setList(it) }
        })
    }
}