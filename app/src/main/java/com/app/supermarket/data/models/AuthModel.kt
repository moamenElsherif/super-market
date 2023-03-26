package com.app.supermarket.data.models

import com.google.gson.Gson

data class AuthModel (
    var accessToken : String? = null,
    var refreshToken : String? = null,
    var userId : Int? = null,
    var isLoggedIn : Boolean = false
){

    override fun toString(): String {
        return Gson().toJson(this)
    }
}