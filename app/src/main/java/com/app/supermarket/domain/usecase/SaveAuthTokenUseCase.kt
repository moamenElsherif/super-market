package com.app.supermarket.domain.usecase

import com.app.supermarket.base.SharedPrefHelper
import com.app.supermarket.data.models.response.LoginResponse
import javax.inject.Inject

class SaveAuthTokenUseCase @Inject constructor(
    private val sharedPrefHelper: SharedPrefHelper
) {

    operator fun invoke(loginResponse: LoginResponse) {
        sharedPrefHelper.addString(SharedPrefHelper.ACCESS_TOKEN, loginResponse.accessToken)
        sharedPrefHelper.addString(SharedPrefHelper.ENCRYPTED_ACCESS_TOKEN, loginResponse.encryptedAccessToken)
        sharedPrefHelper.addInt(SharedPrefHelper.EXPIRE_IN_SECONDS, loginResponse.expireInSeconds)
        sharedPrefHelper.addInt(SharedPrefHelper.USER_ID, loginResponse.userId)
    }
}