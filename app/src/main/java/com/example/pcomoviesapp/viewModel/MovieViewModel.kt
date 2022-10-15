package com.example.pcomoviesapp.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pcomoviesapp.model.Movie
import com.example.pcomoviesapp.repository.AppRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    private var appRepository : AppRepository? = null
    private var mPopular = MutableLiveData<List<Movie>>()
    private var mFavorites = MutableLiveData<List<Movie>>()
    val popular : LiveData<List<Movie>> get() = mPopular
    val favorites : LiveData<List<Movie>> get() = mFavorites

    private fun initializeRepository(context: Context) {
        if (appRepository == null) {
            appRepository = AppRepository(context)
        }
    }

    fun getPopularMovies(context:Context){
        initializeRepository(context)
        CoroutineScope(Dispatchers.IO).launch {
            appRepository?.requestMovies(
                {
                    if (it.page > 0 && it.results.isNotEmpty()) {
                        mPopular.postValue(it.results)
                    }
                },
                {
                    mPopular.postValue(listOf())
                    it.printStackTrace()
                }
            )
        }
    }

    fun getFavoriteMovies(context:Context){
        initializeRepository(context)
        CoroutineScope(Dispatchers.IO).launch {
            mFavorites.postValue(appRepository?.getFavoriteMovies())
        }
    }
}