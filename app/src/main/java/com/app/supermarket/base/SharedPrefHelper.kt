package com.app.supermarket.base

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class SharedPrefHelper(
    private val context: Context
) {

    companion object {
        private const val AUTH = "auth"
        const val ACCESS_TOKEN = "access_token"
        const val ENCRYPTED_ACCESS_TOKEN = "encrypted_access_token"
        const val EXPIRE_IN_SECONDS = "expire_in_seconds"
        const val USER_ID = "user_id"
    }

    private val sharedPreferences: SharedPreferences
        get() = context.getSharedPreferences(AUTH, Context.MODE_PRIVATE)


    fun addString(key: String, value: String) {
        sharedPreferences.edit {
            putString(key, value)
            apply()
        }
    }

    fun addBoolean(key: String, value: Boolean) {
        sharedPreferences.edit {
            putBoolean(key, value)
            apply()
        }
    }

    fun addInt(key: String, value: Int) {
        sharedPreferences.edit {
            putInt(key, value)
            apply()
        }
    }

    fun removeString(key: String) {
        sharedPreferences.edit {
            remove(key)
            apply()
        }
    }

    fun removeAll() {
        sharedPreferences.edit {
            removeAll()
            apply()
        }
    }

    fun getString(key: String): String? = sharedPreferences.getString(key, null)
    fun getBoolean(key: String): Boolean = sharedPreferences.getBoolean(key, false)
}