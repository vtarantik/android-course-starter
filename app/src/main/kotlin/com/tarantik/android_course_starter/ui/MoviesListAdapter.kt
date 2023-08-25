package com.tarantik.android_course_starter.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tarantik.android_course_starter.util.Movie
import cz.tarantik.android_course_starter.databinding.ItemMoviesListBinding

class MoviesListAdapter(): ListAdapter<Movie, MoviesListAdapter.MovieViewHolder>(MovieDiffCallback) {

    class MovieViewHolder(private val binding: ItemMoviesListBinding): RecyclerView.ViewHolder(binding.root) {
        val moviePoster: ImageView = binding.ivMoviePoster
        val description: TextView? = binding.tvDescription

        fun bind(movie: Movie) {
            moviePoster.load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
            description?.post {
                description.text = movie.overview.replace("...", "?")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMoviesListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    object MovieDiffCallback: DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

    }
}