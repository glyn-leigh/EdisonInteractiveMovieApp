package com.example.edisoninteractivemovieapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.edisoninteractivemovieapp.data.RetrofitBuilder
import com.example.edisoninteractivemovieapp.model.Genre
import com.example.edisoninteractivemovieapp.model.GenreX
import com.example.edisoninteractivemovieapp.model.MovieResult
import com.example.edisoninteractivemovieapp.model.PopularMovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailsViewModel:ViewModel(){
    private val genreLiveData: MutableLiveData<List<GenreX>> = MutableLiveData()
    fun getGenre(): LiveData<List<GenreX>> {

        RetrofitBuilder.getGenreData()
            .enqueue(object: Callback<Genre> {
                override fun onResponse(
                    call: Call<Genre>,
                    response: Response<Genre>?
                ) {
                    Log.d("TAG_X", "Response -> ${response?.body()}")
                    response?.let {
                        it.body()?.let { genre ->
                            genreLiveData.value = if(!genre.genres.isNullOrEmpty()) genre.genres else mutableListOf()
                        }
                    }?: setEmptyList()
                }

                override fun onFailure(call: Call<Genre>, t: Throwable) {
                    Log.e("ERROR",  "${t.localizedMessage.toString()}")
                }
            })

        return genreLiveData
    }
    private fun setEmptyList(){
        genreLiveData.value  = mutableListOf()
    }
}