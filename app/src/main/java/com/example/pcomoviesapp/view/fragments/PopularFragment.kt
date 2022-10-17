package com.example.pcomoviesapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pcomoviesapp.R
import com.example.pcomoviesapp.databinding.FragmentPopularBinding
import com.example.pcomoviesapp.dialog.DetailDialog
import com.example.pcomoviesapp.model.Movie
import com.example.pcomoviesapp.view.adapter.MovieAdapter
import com.example.pcomoviesapp.viewModel.MovieViewModel


class PopularFragment : Fragment() {

    private lateinit var binding: FragmentPopularBinding
    private val viewModel by viewModels<MovieViewModel>()
    private var populars = arrayListOf<Movie>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPopularBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeObserver()
        binding.ivPrev.setOnClickListener {
            if (viewModel.response.page > 1) {
                binding.progressBar.visibility = View.VISIBLE
                binding.rvPopularMovies.visibility = View.GONE
                binding.tvNoPopulars.visibility = View.GONE
                viewModel.getPopularMovies(requireContext(), viewModel.response.page - 1)
            }
        }
        binding.ivNext.setOnClickListener{
            if (viewModel.response.page > 0 && viewModel.response.page < viewModel.response.totalPages) {
                binding.progressBar.visibility = View.VISIBLE
                binding.rvPopularMovies.visibility = View.GONE
                binding.tvNoPopulars.visibility = View.GONE
                viewModel.getPopularMovies(requireContext(), viewModel.response.page + 1)
            }
        }
        binding.progressBar.visibility = View.VISIBLE
        binding.tvNoPopulars.visibility = View.GONE
        viewModel.getPopularMovies(requireContext(), 1)
    }

    private fun initializeObserver() {
        viewModel.popular.observe(requireActivity()) {
            if (it.isNotEmpty()) {
                populars = ArrayList()
                populars.addAll(it)
                binding.progressBar.visibility = View.VISIBLE
                binding.tvNoPopulars.visibility = View.GONE
                viewModel.getFavoriteMovies(requireContext())
            } else {
                showNoMoviesAlert()
            }
        }
        viewModel.favorites.observe(requireActivity()) {
            if (it.isNotEmpty()) {
                setupAdapter(viewModel.markFavoriteMovies(populars = populars, favorites = it))
            } else if (populars.isNotEmpty()) {
                setupAdapter(populars)
            } else {
                showNoMoviesAlert()
            }
        }
    }

    private fun setupAdapter(list: List<Movie>) {
        binding.progressBar.visibility = View.GONE
        binding.rvPopularMovies.visibility = View.VISIBLE
        binding.tvNoPopulars.visibility = View.GONE
        val adapter = MovieAdapter(requireContext())
        adapter.submitList(list)
        adapter.onItemClicked = {
            val bundle = Bundle()
            val bottomDetail = DetailDialog()
            bundle.putSerializable(DetailDialog.TAG, it)
            bottomDetail.arguments = bundle
            bottomDetail.show(parentFragmentManager, DetailDialog.TAG)
        }
        adapter.onFavoriteClicked = { movie, _ ->
            if (movie.isFavorite) {
                viewModel.insertFavoriteMovie(requireContext(), movie)
            } else {
                viewModel.deleteFavoriteMovie(requireContext(), movie)
            }
        }
        binding.rvPopularMovies.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPopularMovies.adapter = adapter

        binding.ivNext.setImageResource(
            if (viewModel.response.totalPages > 1 && viewModel.response.page < viewModel.response.totalPages) {
                R.drawable.ic_arrow_forward_enabled
            } else {
                R.drawable.ic_arrow_forward
            }
        )
        binding.ivPrev.setImageResource(
            if (viewModel.response.page > 1) {
                R.drawable.ic_arrow_back_enabled
            } else {
                R.drawable.ic_arrow_back
            }
        )
        binding.tvPageIndex.text = viewModel.response.page.toString()
    }

    private fun showNoMoviesAlert() {
        binding.progressBar.visibility = View.GONE
        binding.tvNoPopulars.visibility = View.VISIBLE
    }

}