package com.app.supermarket.presentation.authentication.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.supermarket.base.BaseResponse
import com.app.supermarket.base.Resource
import com.app.supermarket.base.isValidEmail
import com.app.supermarket.data.models.request.RegisterRequest
import com.app.supermarket.data.models.response.RegisterResponse
import com.app.supermarket.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
): ViewModel() {
    private val _registerEvents = MutableLiveData<RegisterEvents>()
    val registerEvents: MutableLiveData<RegisterEvents> = _registerEvents

    private val _registerResponse = MutableStateFlow<Resource<BaseResponse<RegisterResponse>>>(Resource.Default)
    val registerResponse: MutableStateFlow<Resource<BaseResponse<RegisterResponse>>> = _registerResponse

    private fun verifyData(name: String? , email: String? , password: String?) : Boolean{
        if (name.isNullOrEmpty()) {
            _registerEvents.value = RegisterEvents.InvalidName
            return false
        }
        if (email.isNullOrEmpty()){
            _registerEvents.value = RegisterEvents.EmptyEmail
            return false
        }
        if (password.isNullOrEmpty()){
            _registerEvents.value = RegisterEvents.InvalidPassword
            return false
        }

        if(!email.isValidEmail()){
            _registerEvents.value = RegisterEvents.InvalidEmail
            return false
        }
        return true
    }

    fun registerUser(name: String , email: String , password: String){
        viewModelScope.launch {
            if (verifyData(name , email , password)){
                registerUseCase.invoke(RegisterRequest(
                    deviceType = 0,
                    emailAddress = email,
                    name = name,
                    password = password,
                    language = "en",
                    phoneNumber = "01112892080",
                    fcm = "testtest"
                )).collectLatest {
                    _registerResponse.value = it
                }
            }
        }
    }
}