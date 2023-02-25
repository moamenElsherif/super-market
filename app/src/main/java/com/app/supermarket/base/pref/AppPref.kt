package com.app.supermarket.base.pref

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object AppPref {
    private const val SHARED_PREF = "SUPER_STATE"
    private const val LOCAL_LANG = "local_lang"
    var sharedPreference: SharedPreferences? = null


    fun init(context: Context) {
        sharedPreference = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
    }

    fun setLocalLang(lang: String){
        sharedPreference?.edit { putString(LOCAL_LANG, lang) }
    }

    fun getLocalLang(): String{
        return sharedPreference?.getString(LOCAL_LANG ,"")?: ""
    }
}