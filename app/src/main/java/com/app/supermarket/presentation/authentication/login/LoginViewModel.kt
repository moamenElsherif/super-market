package com.app.supermarket.presentation.authentication.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.supermarket.base.BaseResponse
import com.app.supermarket.base.Resource
import com.app.supermarket.base.isValidEgyNumber
import com.app.supermarket.data.models.request.LoginRequest
import com.app.supermarket.data.models.response.LoginResponse
import com.app.supermarket.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _loginEvents = MutableLiveData<LoginEvents>()
    val loginEvents: MutableLiveData<LoginEvents> = _loginEvents

    private val _logInResponse =
        MutableStateFlow<Resource<BaseResponse<LoginResponse>>>(Resource.Default)
    val logInResponse = _logInResponse


    private fun verifyNumberPassword(phoneNumber: String?, password: String?): Boolean {
        if (password.isNullOrEmpty()) {
            _loginEvents.value = LoginEvents.PasswordEmpty
            return false
        }
        if (phoneNumber.isNullOrEmpty()) {
            _loginEvents.value = LoginEvents.PhoneNumberEmpty
            return false
        }
        if (!phoneNumber.isValidEgyNumber()) {
            _loginEvents.value = LoginEvents.InvalidNumber
            return false
        }

        return true
    }

    fun login(phoneNumber: String?, password: String?) {
        viewModelScope.launch {
            if (verifyNumberPassword(phoneNumber, password)) {
                loginUseCase.invoke(
                    LoginRequest(
                        fcm = "string",
                        password = "string",
                        phoneNumber = "string"
                    )
                ).collectLatest {
                    _logInResponse.value = it
                }
            }
        }

    }
}