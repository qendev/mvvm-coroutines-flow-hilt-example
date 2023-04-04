package com.example.mvvm_coroutines_flow_hilt_example.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvm_coroutines_flow_hilt_example.data.Movie
import com.example.mvvm_coroutines_flow_hilt_example.databinding.MovieItemBinding
import javax.inject.Inject

class MovieAdapter @Inject constructor():RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    var movies = mutableListOf<Movie>()
    private var clickInterface:ClickInterface<Movie>? = null

    fun updateMovies(movies:List<Movie>){
        this.movies = movies.toMutableList()
        notifyItemRangeInserted(0,movies.size)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int, ):MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = MovieItemBinding.inflate(inflater,parent,false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.view.tvTitle.text =movie.title
        holder.view.rvYear.text ="Year: ${movie.year}"
        holder.view.rvRating.text ="Rating:${movie.imDbRating}"
        Glide.with(holder.view.imgMovieImage.context)
            .load(movie.image)
            .centerCrop()
            .into(holder.view.imgMovieImage)
        holder.view.movieCard.setOnClickListener {
            clickInterface?.onClick(movie)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun setItemClick(clickInterface:ClickInterface<Movie>){
        this.clickInterface = clickInterface
    }

    interface ClickInterface<T> {
        fun onClick(data: T)

    }

    class MovieViewHolder(val view: MovieItemBinding):RecyclerView.ViewHolder(view.root)



}