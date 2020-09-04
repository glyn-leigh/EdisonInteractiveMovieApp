package com.example.edisoninteractivemovieapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.edisoninteractivemovieapp.data.DataGetter
import com.example.edisoninteractivemovieapp.data.RetrofitBuilder
import com.example.edisoninteractivemovieapp.model.MovieResult
import com.example.edisoninteractivemovieapp.model.PopularMovieResponse
import com.example.edisoninteractivemovieapp.utils.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel: ViewModel() {

    private val movieLiveData: MutableLiveData<List<MovieResult>> = MutableLiveData()
    fun getMovieData(): LiveData<List<MovieResult>> {

        RetrofitBuilder.getAllMovieData()
            .enqueue(object: Callback<PopularMovieResponse>{
                override fun onResponse(
                    call: Call<PopularMovieResponse>,
                    response: Response<PopularMovieResponse>?
                ) {
                    Log.d("TAG_X", "Response -> ${response?.body()}")
                    response?.let {
                        it.body()?.let { popularMovies ->
                            movieLiveData.value = if(!popularMovies.results.isNullOrEmpty()) popularMovies.results else mutableListOf()
                        }
                    }?: {movieLiveData.value  = mutableListOf()}
                }

                override fun onFailure(call: Call<PopularMovieResponse>, t: Throwable) {
                    Log.e("ERROR",  "${t.localizedMessage.toString()}")
                }
            })

        return movieLiveData
    }




}