package com.app.supermarket.data.models.response

data class LoginResponse(
    val accessToken: String,
    val encryptedAccessToken: String,
    val expireInSeconds: Int,
    val userId: Int
)