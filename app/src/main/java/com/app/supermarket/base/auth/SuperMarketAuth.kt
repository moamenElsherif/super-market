package com.app.supermarket.base.auth

import com.app.supermarket.base.AuthPreference
import com.app.supermarket.data.models.AuthModel

open class SuperMarketAuth(private var auth: AuthPreference) : Auth {

    override fun getAccessToken(): String? {
        return getAuthData().accessToken
    }

    override fun getRefreshToken(): String? {
        return getAuthData().refreshToken
    }

    override fun getAuthData(): AuthModel {
        return auth.getAuthData()
    }

    override fun saveAuthData(authData: AuthModel?) {
        authData?.let { auth.saveAuthData(it) }
    }

    override fun isLoggedIn(): Boolean {
        return auth.isLoggedIn()
    }

    override fun logout() {
        auth.resetAuthData()
    }
}