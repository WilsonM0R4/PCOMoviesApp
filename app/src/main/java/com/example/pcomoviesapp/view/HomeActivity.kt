package com.example.pcomoviesapp.view

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.pcomoviesapp.R
import com.example.pcomoviesapp.databinding.ActivityHomeBinding

class HomeActivity : FragmentActivity() {

    private lateinit var binding:ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHost = supportFragmentManager.findFragmentById(R.id.homeFragmentContainer) as NavHostFragment
        binding.homeBottomNavigation.setupWithNavController(navHost.navController)
        binding.homeBottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.popular -> {
                    navHost.navController.navigate(R.id.popularFragment)
                }
                R.id.favorites -> {
                    navHost.navController.navigate(R.id.favoritesFragment)
                }
            }
            true
        }
    }
}