package com.app.supermarket.data.repo

import com.app.supermarket.base.BaseResponse
import com.app.supermarket.base.Resource
import com.app.supermarket.data.models.request.LoginRequest
import com.app.supermarket.data.models.response.LoginResponse

interface Repository {
    suspend fun login(loginRequest: LoginRequest): Resource<BaseResponse<LoginResponse>>
}