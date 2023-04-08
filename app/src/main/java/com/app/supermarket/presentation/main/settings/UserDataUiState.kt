package com.app.supermarket.presentation.main.settings

data class UserDataUiState(
    val id: Int,
    val userName: String,
    val name: String,
    val surname: String,
    val emailAddress: String,
    val isActive: Boolean,
    val fullName: String,
)