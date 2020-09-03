package com.example.edisoninteractivemovieapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.edisoninteractivemovieapp.R
import com.example.edisoninteractivemovieapp.model.GenreX
import com.example.edisoninteractivemovieapp.viewmodel.MovieDetailsViewModel
import com.example.edisoninteractivemovieapp.viewmodel.MovieDetailsViewModelFactory
import com.example.edisoninteractivemovieapp.viewmodel.MovieViewModel
import com.example.edisoninteractivemovieapp.viewmodel.MovieViewModelFactory

class MovieDetailsActivity : AppCompatActivity() {


    private val movieDetailVM: MovieDetailsViewModel by viewModels( factoryProducer = { MovieDetailsViewModelFactory })
    lateinit var allGenreList: List<GenreX>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        val bundle = intent.extras
        if (bundle != null) {
            var movieTitle = bundle.getString("movieTitle", "Title").toString()
            var movieSummary = bundle.getString("movieDescription", "Description").toString()
            var movieImage = bundle.getString("imageURL").toString()

            val currentImage:ImageView =  findViewById(R.id.image)
            val currentTitle:TextView = findViewById(R.id.title)
            val currentDescription:TextView = findViewById(R.id.movieDesc)
            val currentGenres:TextView = findViewById(R.id.movieGenre)
            Glide.with(this).load("http://image.tmdb.org/t/p/w500${movieImage}").into(currentImage)
            currentTitle.text = movieTitle
            currentDescription.text = movieSummary

            movieDetailVM.getGenre().observe(this,{
                allGenreList = it
                currentGenres.text =  convertGenres(allGenreList).toString()

            })






        }


    }

    fun convertGenres(allGenres:List<GenreX>):ArrayList<String>{
        val bundle = intent.extras
        var genreList = bundle?.getIntArray("movieGenres") as IntArray
        var finalList:ArrayList<String> = arrayListOf()

        for (genres in genreList){

            for (all in allGenres){
                if (all.id == genres){
                    finalList.add(all.name)
                }
            }
        }

        return finalList

    }
}