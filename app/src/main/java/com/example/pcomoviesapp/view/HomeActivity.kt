package com.example.pcomoviesapp.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.pcomoviesapp.R
import com.example.pcomoviesapp.databinding.ActivityHomeBinding
import com.example.pcomoviesapp.viewModel.UserViewModel

class HomeActivity : FragmentActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel by viewModels<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHost =
            supportFragmentManager.findFragmentById(R.id.homeFragmentContainer) as NavHostFragment
        binding.homeBottomNavigation.setupWithNavController(navHost.navController)
        binding.homeBottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.popular -> {
                    navHost.navController.navigate(R.id.popularFragment)
                }
                R.id.favorites -> {
                    navHost.navController.navigate(R.id.favoritesFragment)
                }
            }
            true
        }
        binding.ivLogout.setOnClickListener {
            val sharedPreferences =
                getSharedPreferences(getString(R.string.shared_session), Context.MODE_PRIVATE)
            sharedPreferences.edit().putString(getString(R.string.active_user), "").apply()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        val activeUser = intent.getStringExtra(getString(R.string.active_user))
        initializeObserver()
        viewModel.getUser(this, activeUser!!)
    }

    private fun initializeObserver() {
        viewModel.user.observe(this) {
            if (it.name.isNotEmpty()) {
                val title = "${getString(R.string.home_welcome_user)}, ${it.name}"
                binding.tvToolbarTitle.text = title
            }
        }
    }
}