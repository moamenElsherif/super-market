package com.app.supermarket.presentation.main

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.app.supermarket.R
import com.app.supermarket.base.BaseActivity
import com.app.supermarket.base.SharedPrefHelper
import com.app.supermarket.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(){
    override val layoutRes: Int
        get() = R.layout.activity_main

    override fun initUI(savedInstanceState: Bundle?) {
        handleBottomNav()
    }

    @Inject
    lateinit var sharedPrefHelper : SharedPrefHelper
    private fun handleBottomNav() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        val navController = navHostFragment.navController

        binding.apply {
            bottomNav.setupWithNavController(navController)
        }

        val accessToken = sharedPrefHelper.getString(SharedPrefHelper.ACCESS_TOKEN)
        if (accessToken.isNullOrEmpty()) {
            Timber.d("Is Null Auth Token")
        } else {
            Timber.d("Not null => $accessToken")
        }
    }
}