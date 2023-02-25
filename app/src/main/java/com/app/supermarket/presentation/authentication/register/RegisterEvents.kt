package com.app.supermarket.presentation.authentication.register

sealed class RegisterEvents {
    object InvalidName: RegisterEvents()
    object EmptyEmail: RegisterEvents()
    object InvalidEmail: RegisterEvents()
    object InvalidPassword: RegisterEvents()
}