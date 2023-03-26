package com.app.supermarket.domain.usecase

import com.app.supermarket.base.auth.Auth
import com.app.supermarket.data.models.AuthModel
import com.app.supermarket.data.models.response.LoginResponse
import javax.inject.Inject

class SaveAuthTokenUseCase @Inject constructor(
    private val authPreference: Auth
) {

    operator fun invoke(loginResponse: LoginResponse) {
        val authModel = AuthModel(
            accessToken = loginResponse.accessToken,
            refreshToken = "",
            userId = loginResponse.userId,
            isLoggedIn = loginResponse.accessToken.isNotEmpty()
        )
        authPreference.saveAuthData(authModel)
    }
}