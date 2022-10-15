package com.example.pcomoviesapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pcomoviesapp.R
import com.example.pcomoviesapp.common.Constants
import com.example.pcomoviesapp.databinding.MovieItemBinding
import com.example.pcomoviesapp.model.Movie
import com.squareup.picasso.Picasso

class MovieAdapter(private val context:Context) : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            context,
            MovieItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class MovieViewHolder(private val context: Context, private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(movie: Movie) {
            binding.tvItemMovieTitle.text = movie.title
            binding.tvItemOriginalTitle.text = context.getString(
                R.string.popular_original_title,
                movie.originalTitle
            )
            binding.tvItemPopularity.text = context.getString(
                R.string.popular_popularity,
                movie.popularity.toString()
            )
            binding.tvItemReleaseDate.text = context.getString(
                R.string.popular_release_date,
                movie.releaseDate
            )
            Picasso.get().load(Constants.IMAGE_MAIN_PATH + movie.posterPath)
                .into(binding.ivPosterPreview)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }
}