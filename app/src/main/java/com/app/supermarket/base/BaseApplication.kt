package com.app.supermarket.base

import android.app.Application
import com.app.supermarket.base.pref.AppPref
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.Forest.plant


@HiltAndroidApp
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        plant(Timber.DebugTree())
        AppPref.init(applicationContext)
    }
}