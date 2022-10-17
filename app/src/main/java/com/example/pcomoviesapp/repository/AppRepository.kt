package com.example.pcomoviesapp.repository

import android.content.Context
import com.example.pcomoviesapp.db.MoviesAppDB
import com.example.pcomoviesapp.db.entities.format
import com.example.pcomoviesapp.model.*
import com.example.pcomoviesapp.network.MoviesApiClient
import com.example.pcomoviesapp.network.ResponseCallback
import java.lang.Exception

class AppRepository(private val context:Context) {

    private val persistence = MoviesAppDB.getInstance(context)
    private val remote = MoviesApiClient()

    suspend fun registerUser(user:User) : Boolean{
        return try {
            persistence.userDAO().insert(user.forPersistence())
            getUser(user.sessionName).name == user.name
        } catch (ex:Exception) {
            ex.printStackTrace()
            false
        }
    }

    suspend fun getUser(sessionName:String) : User {
        return persistence.userDAO().getInfo(sessionName).format()
    }

    suspend fun insertMovie(movie:Movie) : Boolean{
        return try {
            persistence.moviesDAO().insert(movie.toPersistence())
            persistence.moviesDAO().getDetail(movie.id)
            true
        } catch (ex:Exception){
            ex.printStackTrace()
            false
        }
    }

    suspend fun insertMovies(movies:List<Movie>) {
        persistence.moviesDAO().insert(movies.map {
            it.toPersistence()
        })
    }

    suspend fun updateMovie(movie:Movie) {
        persistence.moviesDAO().update(movie.toPersistence())
    }

    suspend fun getAllMovies() : List<Movie> {
        return persistence.moviesDAO().getAll().map {
            it.format()
        }
    }

    suspend fun getMovieDetail(id:Int) : Movie {
        return persistence.moviesDAO().getDetail(id).format()
    }

    suspend fun getFavoriteMovies() : List<Movie> {
        return persistence.moviesDAO().getFavorites().map {
            it.format()
        }
    }

    suspend fun deleteFavoriteMovie(movie:Movie) : Boolean {
        return try {
            persistence.moviesDAO().delete(movie.toPersistence())
            persistence.moviesDAO().getDetail(movie.id).id != 0
        } catch (ex:Exception) {
            true
        }
    }

    fun requestMovies(page:Int=1,onSuccess:(Response)->Unit, onFail:(ex:Throwable)->Unit) {
        remote.getLatestMovies(context, page, object:ResponseCallback {
            override fun onSuccess(response:ApiResponse) {
                onSuccess.invoke(response.format())
            }

            override fun onFail(ex:Throwable){
                onFail.invoke(ex)
            }
        })
    }
}