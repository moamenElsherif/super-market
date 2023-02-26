package com.app.supermarket.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.app.supermarket.R
import com.app.supermarket.base.BaseActivity
import com.app.supermarket.databinding.ActivityMainBinding
import com.app.supermarket.presentation.main.cart.CartFragment
import com.app.supermarket.presentation.main.home.HomeFragment
import com.app.supermarket.presentation.main.settings.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(){
    override val layoutRes: Int
        get() = R.layout.activity_main

    lateinit var bottomNav : BottomNavigationView

    override fun initUI(savedInstanceState: Bundle?) {
        bottomNav = binding.bottomNav
        handleBottomNav()
    }

    private fun handleBottomNav() {
        loadFragment(HomeFragment())
        bottomNav.selectedItemId = R.id.home
        bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.cart -> {
                    loadFragment(CartFragment())
                    true
                }
                R.id.home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.settings -> {
                    loadFragment(SettingsFragment())
                    true
                }
                else -> {
                    loadFragment(HomeFragment())
                    true
                }
            }
        }
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }

}