package com.example.pcomoviesapp.repository

import android.app.Application
import com.example.pcomoviesapp.db.MoviesAppDB
import com.example.pcomoviesapp.db.entities.format
import com.example.pcomoviesapp.model.Movie
import com.example.pcomoviesapp.model.User
import com.example.pcomoviesapp.model.forPersistence
import com.example.pcomoviesapp.model.toPersistence

class AppRepository(application: Application) {

    private val persistence = MoviesAppDB.getInstance(application)

    fun registerUser(user:User) {
        persistence.userDAO().insert(user.forPersistence())
    }

    fun getUser(sessionName:String) : List<User> {
        return persistence.userDAO().getInfo(sessionName).map {
            it.format()
        }
    }

    fun insertMovie(movie:Movie){
        persistence.moviesDAO().insert(movie.toPersistence())
    }

    fun insertMovies(movies:List<Movie>) {
        persistence.moviesDAO().insert(movies.map {
            it.toPersistence()
        })
    }

    fun updateMovie(movie:Movie) {
        persistence.moviesDAO().update(movie.toPersistence())
    }

    fun getAllMovies() : List<Movie> {
        return persistence.moviesDAO().getAll().map {
            it.format()
        }
    }

    fun getFavoriteMovies() : List<Movie> {
        return persistence.moviesDAO().getFavorites().map {
            it.format()
        }
    }

    fun requestMovies() {

    }
}