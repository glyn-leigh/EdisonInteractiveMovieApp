package com.example.edisoninteractivemovieapp.data

import com.example.edisoninteractivemovieapp.model.Genre
import com.example.edisoninteractivemovieapp.model.GenreX
import com.example.edisoninteractivemovieapp.model.PopularMovieResponse
import com.example.edisoninteractivemovieapp.utils.API_KEY
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private lateinit var client: OkHttpClient
    private val logging = HttpLoggingInterceptor()

    private var dataGetter: DataGetter

    init {
        dataGetter = buildService(createRetrofit())
    }

    private fun createRetrofit(): Retrofit {

        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        client = OkHttpClient.Builder()
            .addInterceptor(logging).build()

        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    private fun buildService(retrofit: Retrofit): DataGetter {
        return retrofit.create(DataGetter::class.java)
    }

    fun getAllMovieData(): Call<PopularMovieResponse> = dataGetter.getMovies(API_KEY)

    fun getGenreData():Call<Genre> = dataGetter.getGenres(API_KEY)
}