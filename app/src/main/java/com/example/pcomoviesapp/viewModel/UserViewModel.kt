package com.example.pcomoviesapp.viewModel


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pcomoviesapp.model.User
import com.example.pcomoviesapp.repository.AppRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private var repository : AppRepository? = null
    private var mUser = MutableLiveData<User>()
    private var mRegister = MutableLiveData<Boolean>()
    val user: LiveData<User> get() = mUser
    val register : LiveData<Boolean> get() = mRegister

    fun getUser(context: Context, sessionName: String) {
        initRepository(context)
        CoroutineScope(Dispatchers.IO).launch {
            mUser.postValue(repository?.getUser(sessionName))
        }
    }

    fun registerUser(context: Context, sessionName: String, name: String, password: String) {
        initRepository(context)
        CoroutineScope(Dispatchers.IO).launch {
            val user = User(sessionName = sessionName, name = name, password = password)
            val registered = repository?.registerUser(user)
            mRegister.postValue(registered ?: false)
        }
    }

    private fun initRepository(context:Context) {
        if (repository==null) {
            repository = AppRepository(context)
        }
    }

}