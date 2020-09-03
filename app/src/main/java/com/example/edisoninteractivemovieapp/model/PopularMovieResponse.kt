package com.example.edisoninteractivemovieapp.model

data class PopularMovieResponse(
    val page: Int,
    val results: List<MovieResult>,
    val total_pages: Int,
    val total_results: Int
)