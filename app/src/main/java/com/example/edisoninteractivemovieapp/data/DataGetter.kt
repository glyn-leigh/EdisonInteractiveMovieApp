package com.example.edisoninteractivemovieapp.data

import com.example.edisoninteractivemovieapp.model.Genre
import com.example.edisoninteractivemovieapp.model.GenreX
import com.example.edisoninteractivemovieapp.model.PopularMovieResponse
import com.example.edisoninteractivemovieapp.utils.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DataGetter {
    @GET("/3/movie/popular")
    fun getMovies(@Query("api_key") key: String): Call<PopularMovieResponse>
    @GET("/3/genre/movie/list")
    fun getGenres(@Query("api_key") key:String): Call<Genre>
}
