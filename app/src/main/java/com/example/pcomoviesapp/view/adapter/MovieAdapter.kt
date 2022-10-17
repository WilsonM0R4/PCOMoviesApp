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

class MovieAdapter(private val context: Context) :
    ListAdapter<Movie, MovieAdapter.MovieViewHolder>(DiffCallback()) {

    var onFavoriteClicked : (Movie, Int) -> Unit = {_, _-> }
    var onItemClicked : (Movie) -> Unit = {}

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
        holder.onBind(getItem(position), position, onItemClicked, onFavoriteClicked)
    }

    class MovieViewHolder(private val context: Context, private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(movie: Movie,
                   itemListIndex:Int,
                   onItemClicked:(Movie)->Unit,
                   onFavoriteClicked:(Movie, Int)->Unit) {
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
            binding.ivItemFavorite.setImageResource(
                if (movie.isFavorite){
                    R.drawable.ic_favorites_fill
                } else {
                    R.drawable.ic_favorites
                }
            )
            Picasso.get().load(Constants.IMAGE_MAIN_PATH + movie.posterPath)
                .into(binding.ivPosterPreview)
            binding.ivItemFavorite.setOnClickListener {
                binding.ivItemFavorite.setImageResource(
                    if (movie.isFavorite){
                        R.drawable.ic_favorites
                    } else {
                        R.drawable.ic_favorites_fill
                    }
                )
                movie.isFavorite = !movie.isFavorite
                onFavoriteClicked.invoke(movie, itemListIndex)
            }
            binding.cvMovieItem.setOnClickListener {
                onItemClicked.invoke(movie)
            }
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