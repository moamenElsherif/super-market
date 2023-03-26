package com.app.supermarket.base.auth

import com.app.supermarket.data.models.AuthModel

interface Auth {
    fun getAccessToken(): String?
    fun getRefreshToken(): String?
    fun getAuthData(): AuthModel
    fun saveAuthData(authData: AuthModel?)
    fun isLoggedIn(): Boolean
    fun logout()
}