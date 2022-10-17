package com.example.pcomoviesapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pcomoviesapp.databinding.FragmentFavoritesBinding
import com.example.pcomoviesapp.dialog.DetailDialog
import com.example.pcomoviesapp.model.Movie
import com.example.pcomoviesapp.view.adapter.MovieAdapter
import com.example.pcomoviesapp.viewModel.MovieViewModel

class FavoritesFragment : Fragment() {

    private lateinit var binding : FragmentFavoritesBinding
    private val viewModel by viewModels<MovieViewModel>()
    private var favMovies = mutableListOf<Movie>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeObserver()
        viewModel.getFavoriteMovies(requireContext())
    }

    private fun initializeObserver() {
        viewModel.favorites.observe(requireActivity()) {
            binding.progressBar.visibility = View.GONE
            if (it.isNotEmpty()) {
                setupAdapter(it)
            } else {
                binding.tvNoFavorites.visibility = View.VISIBLE
            }
        }
    }

    private fun setupAdapter(favorites:List<Movie>) {
        val adapter = MovieAdapter(requireContext())
        favMovies = favorites.toMutableList()
        adapter.submitList(favMovies)
        adapter.onItemClicked = {
            val bundle = Bundle()
            val bottomDetail = DetailDialog()
            bundle.putSerializable(DetailDialog.TAG, it)
            bottomDetail.arguments = bundle
            bottomDetail.show(parentFragmentManager, DetailDialog.TAG)
        }
        adapter.onFavoriteClicked = { movie, index ->
            movie.isFavorite = true
            favMovies.remove(movie)
            adapter.notifyItemRemoved(index)
            viewModel.deleteFavoriteMovie(requireContext(), movie)
        }
        binding.rvFavoriteMovies.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFavoriteMovies.adapter = adapter
        binding.tvNoFavorites.visibility = View.GONE
    }

}