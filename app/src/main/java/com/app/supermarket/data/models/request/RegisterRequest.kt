package com.app.supermarket.data.models.request

data class RegisterRequest(
    val deviceType: Int,
    val emailAddress: String,
    val fcm: String,
    val language: String,
    val name: String,
    val password: String,
    val phoneNumber: String
)