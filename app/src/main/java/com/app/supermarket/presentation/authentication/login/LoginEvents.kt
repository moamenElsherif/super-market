package com.app.supermarket.presentation.authentication.login

sealed class LoginEvents  {
    object PhoneNumberEmpty: LoginEvents()
    object PasswordEmpty: LoginEvents()
    object InvalidNumber: LoginEvents()
    object EmptyData: LoginEvents()
}