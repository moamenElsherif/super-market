package com.app.supermarket.presentation.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.app.supermarket.R
import com.app.supermarket.base.BaseActivity
import com.app.supermarket.databinding.ActivitySplashBinding
import com.app.supermarket.presentation.authentication.AuthenticationActivity
import com.app.supermarket.presentation.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override val layoutRes: Int
        get() = R.layout.activity_splash

    override fun initUI(savedInstanceState: Bundle?) {
        initSplashScreen()
        setLocale("ar")
    }

    private fun initSplashScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            openMain()
        }, 3000)
    }

    private fun setLocale(lang: String?) {
        val locale = lang?.let { Locale(it) }
        if (locale != null) {
            Locale.setDefault(locale)
        }
        val config = Configuration()
        config.locale = locale
        this.resources?.updateConfiguration(
            config,
            this.resources?.displayMetrics
        )
    }


    private fun openMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


    private fun openAuth(){
        val intent = Intent(this, AuthenticationActivity::class.java)
        startActivity(intent)
        finish()
    }

}