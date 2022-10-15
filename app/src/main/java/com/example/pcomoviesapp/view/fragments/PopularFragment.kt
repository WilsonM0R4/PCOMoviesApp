package com.example.pcomoviesapp.view.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pcomoviesapp.R
import com.example.pcomoviesapp.databinding.FragmentPopularBinding
import com.example.pcomoviesapp.view.adapter.MovieAdapter
import com.example.pcomoviesapp.viewModel.MovieViewModel


class PopularFragment : Fragment() {

    private lateinit var binding : FragmentPopularBinding
    private val viewModel by viewModels<MovieViewModel>()

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
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getPopularMovies(requireContext())
    }

    private fun initializeObserver(){
        viewModel.popular.observe(requireActivity()) {
            binding.progressBar.visibility = View.GONE
            if (it.isNotEmpty()) {
                val adapter = MovieAdapter(requireContext())
                adapter.submitList(it)
                binding.rvPopularMovies.layoutManager = LinearLayoutManager(requireContext())
                binding.rvPopularMovies.adapter = adapter
            } else {
                val alert = AlertDialog.Builder(requireContext())
                alert.apply {
                    setMessage(R.string.popular_empty_message)
                    setPositiveButton(R.string.understood){_,_->}
                }
                alert.create()
                alert.show()
            }
        }
    }

}