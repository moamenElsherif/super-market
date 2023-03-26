package com.app.supermarket.base

import android.content.Context
import com.app.supermarket.data.models.AuthModel
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Singleton
class AuthPreference(
    @ApplicationContext context: Context
): BasePreferenceStorage(context) {

    companion object {
        private const val AUTH = "auth"
        const val AUTH_DATA = "auth_data"
    }
    fun saveAuthData(authModel: AuthModel){
        putString(AUTH_DATA , authModel.toString())
    }

    fun getAuthData(): AuthModel{
        return try {
            val authData = getString(AUTH_DATA, null)
            Gson().fromJson(authData, AuthModel::class.java)
        } catch (ex: Exception) {
            AuthModel()
        }
    }

    fun resetAuthData() {
        putString(AUTH_DATA, null)
    }

    fun isLoggedIn() = getAuthData().isLoggedIn

}