package com.example.edisoninteractivemovieapp.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.edisoninteractivemovieapp.R
import com.example.edisoninteractivemovieapp.adapter.MovieListingAdapter
import com.example.edisoninteractivemovieapp.model.MovieResult
import com.example.edisoninteractivemovieapp.viewmodel.MovieViewModel
import com.example.edisoninteractivemovieapp.viewmodel.MovieViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MovieListingsActivity : AppCompatActivity() {

    private val movieViewModel: MovieViewModel by viewModels( factoryProducer = {MovieViewModelFactory})
    private val movieListingAdapter: MovieListingAdapter = MovieListingAdapter(mutableListOf(),this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        move_listings_recyclerview.adapter = movieListingAdapter
        move_listings_recyclerview.addItemDecoration(DividerItemDecoration(this,LinearLayoutManager.HORIZONTAL))

        movieViewModel.getMovieData().observe(this, {
            displayMovies(it)
        })
    }

    private fun displayMovies(movies: List<MovieResult>) {
        movieListingAdapter.updateMovies(movies)
    }
}

