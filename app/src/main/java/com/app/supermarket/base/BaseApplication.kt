package com.app.supermarket.base

import android.app.Application
import com.app.supermarket.base.pref.AppPref
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppPref.init(applicationContext)
    }
}