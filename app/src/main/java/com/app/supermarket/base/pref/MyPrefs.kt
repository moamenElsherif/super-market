package com.app.supermarket.base.pref


import android.content.Context
import com.app.supermarket.base.BasePreferenceStorage
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class MyPrefs @Inject constructor(@ApplicationContext context: Context) : BasePreferenceStorage(context) {

}