package com.app.supermarket.presentation.authentication.forgetpassword

sealed class  ForgetPasswordEvent {
    object EmptyPassword: ForgetPasswordEvent()
    object NotMatchPassword: ForgetPasswordEvent()
}