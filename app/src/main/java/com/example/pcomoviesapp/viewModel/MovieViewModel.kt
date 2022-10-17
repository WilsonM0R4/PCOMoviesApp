package com.example.pcomoviesapp.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pcomoviesapp.model.Movie
import com.example.pcomoviesapp.model.Response
import com.example.pcomoviesapp.repository.AppRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    private var appRepository : AppRepository? = null
    private var mPopular = MutableLiveData<List<Movie>>()
    private var mFavorites = MutableLiveData<List<Movie>>()
    private var mNewFavorite = MutableLiveData<Boolean>()
    private var mDeletedFavorite = MutableLiveData<Boolean>()
    var response = Response()
    val popular : LiveData<List<Movie>> get() = mPopular
    val favorites : LiveData<List<Movie>> get() = mFavorites

    private fun initializeRepository(context: Context) {
        if (appRepository == null) {
            appRepository = AppRepository(context)
        }
    }

    private fun restartPopular() {
        mPopular = MutableLiveData<List<Movie>>()
    }

    fun getPopularMovies(context:Context, page:Int){
        initializeRepository(context)
        mPopular.postValue(listOf())
        CoroutineScope(Dispatchers.IO).launch {
            appRepository?.requestMovies(page,
                {
                    response = it
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

    fun insertFavoriteMovie(context:Context, movie:Movie) {
        initializeRepository(context)
        CoroutineScope(Dispatchers.IO).launch {
            mNewFavorite.postValue(appRepository?.insertMovie(movie))
        }
    }

    fun deleteFavoriteMovie(context:Context, movie:Movie) {
        initializeRepository(context)
        CoroutineScope(Dispatchers.IO).launch {
            mDeletedFavorite.postValue(appRepository?.deleteFavoriteMovie(movie))
        }
    }

    fun markFavoriteMovies(populars:List<Movie>, favorites:List<Movie>) : List<Movie> {
        if (populars.isNotEmpty() && favorites.isNotEmpty()) {
            favorites.forEach { favorite ->
                populars.forEach { popular ->
                    if (popular.id == favorite.id) {
                        popular.isFavorite = true
                    }
                }
            }
        }
        return populars
    }
}