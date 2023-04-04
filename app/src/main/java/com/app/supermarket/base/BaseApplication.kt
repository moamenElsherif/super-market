package com.app.supermarket.base

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.app.supermarket.base.auth.Auth
import com.app.supermarket.base.pref.AppPref
import dagger.hilt.android.HiltAndroidApp
import org.intellij.lang.annotations.Language
import timber.log.Timber
import timber.log.Timber.Forest.plant
import java.util.*
import javax.inject.Inject


@HiltAndroidApp
class BaseApplication : Application() {

    @Inject
    lateinit var auth: Auth

    override fun onCreate() {
        super.onCreate()
        plant(Timber.DebugTree())
        AppPref.init(applicationContext)
        instance = this
    }


    companion object {
        lateinit var instance: BaseApplication
        fun getAppInstance(): BaseApplication {
            return instance
        }
    }
}