package com.example.edisoninteractivemovieapp.adapter

import android.content.Context
import android.content.Intent
import com.example.edisoninteractivemovieapp.model.MovieResult
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.edisoninteractivemovieapp.R
import com.example.edisoninteractivemovieapp.view.MovieDetailsActivity

class MovieListingAdapter(private var movies: List<MovieResult>, private var context: Context?):RecyclerView.Adapter<MovieListingAdapter.ListingViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return ListingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListingViewHolder, position: Int) {
        holder.itemView.setOnClickListener { navigateToActivity(movies[position]) }
        return holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun updateMovies(movies: List<MovieResult>){
        this.movies = movies
        notifyDataSetChanged()
    }

    class ListingViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        private val photo:ImageView = itemView.findViewById(R.id.img)
        private val title:TextView = itemView.findViewById(R.id.title)
        private val overview:TextView = itemView.findViewById(R.id.desc)
        //private val rating:TextView = itemView.findViewById(R.id.movie_rating)

        fun bind(movie: MovieResult) {
            Glide.with(itemView.context).load("http://image.tmdb.org/t/p/w500${movie.poster_path}").into(photo)
            title.text = movie.title
            overview.text = movie.overview
            }
            //rating.text = "Rating : "+movie.vote_average.toString()
        }



    private fun navigateToActivity(movieInfo:MovieResult){
        val intent = Intent(context, MovieDetailsActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra("movieTitle", movieInfo.title)
        intent.putExtra("movieDescription", movieInfo.overview)
        intent.putExtra("imageURL", movieInfo.poster_path)
        intent.putExtra("movieGenres", movieInfo.genre_ids)
        context!!.startActivity(intent)




    }

}