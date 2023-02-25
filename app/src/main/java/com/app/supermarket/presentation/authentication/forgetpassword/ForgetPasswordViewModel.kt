package com.app.supermarket.presentation.authentication.forgetpassword

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgetPasswordViewModel @Inject constructor(

): ViewModel() {

    private val _forgetPasswordEvent = MutableLiveData<ForgetPasswordEvent>()
    val forgetPasswordEvent: MutableLiveData<ForgetPasswordEvent> = _forgetPasswordEvent

    fun validatePasswords(password: String? , confirmPass: String? ): Boolean{
        if (password.isNullOrEmpty() || confirmPass.isNullOrEmpty()){
            _forgetPasswordEvent.value = ForgetPasswordEvent.EmptyPassword
            return false
        }

        if (password != confirmPass){
            _forgetPasswordEvent.value = ForgetPasswordEvent.NotMatchPassword
            return false
        }

        return true
    }

    fun changePassword(password: String , confirmPass: String){
        if (validatePasswords(password , confirmPass)){

        }
    }
}