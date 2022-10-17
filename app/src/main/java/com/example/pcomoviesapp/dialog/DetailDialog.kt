package com.example.pcomoviesapp.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pcomoviesapp.R
import com.example.pcomoviesapp.common.Constants
import com.example.pcomoviesapp.databinding.FragmentDetailDialogBinding
import com.example.pcomoviesapp.model.Movie
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Picasso

class DetailDialog : BottomSheetDialogFragment() {

    companion object {
        const val TAG = "DetailBottomSheet"
    }

    private lateinit var binding : FragmentDetailDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailDialogBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie : Movie = requireArguments().getSerializable(TAG) as Movie

        Picasso.get().load(Constants.IMAGE_MAIN_PATH+movie.posterPath).into(binding.ivPoster)
        binding.txtTitle.text = movie.title
        binding.txtDescription.text = movie.overview
        binding.txtOriginalLanguage.text = String.format(getString(R.string.original_language), movie.originalLanguage)
        binding.txtOriginalTitle.text = String.format(getString(R.string.original_title), movie.originalTitle)
        binding.txtPopularity.text = String.format(getString(R.string.popularity), movie.popularity)
        binding.txtReleaseDate.text = String.format(getString(R.string.release_date), movie.releaseDate)
        binding.txtVoteAverage.text = String.format(getString(R.string.vote_average), movie.voteAverage)
    }


}