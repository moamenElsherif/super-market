package com.app.supermarket.data.models.request

data class LoginRequest(
    val fcm: String,
    val password: String,
    val phoneNumber: String,
    val rememberClient: Boolean = true
)