package com.app.supermarket.data.models.response

import com.app.supermarket.presentation.main.settings.UserDataUiState

data class UserDataResponse(
    val id: Int,
    val userName: String,
    val name: String,
    val surname: String,
    val emailAddress: String,
    val isActive: Boolean,
    val fullName: String,
    val lastLoginTime: String,
    val creationTime: String,
    val roleNames: List<String>,
) {
    fun toUserDataUiState() = UserDataUiState(id, userName, name, surname, emailAddress, isActive, fullName)
}
