package com.app.supermarket.data.repo

import com.app.supermarket.base.BaseResponse
import com.app.supermarket.base.Resource
import com.app.supermarket.data.models.request.LoginRequest
import com.app.supermarket.data.models.request.RegisterRequest
import com.app.supermarket.data.models.response.CategoryHomeResponse
import com.app.supermarket.data.models.response.GetAllProductResponse
import com.app.supermarket.data.models.response.LoginResponse
import com.app.supermarket.data.models.response.RegisterResponse

interface Repository {
    suspend fun login(loginRequest: LoginRequest): Resource<BaseResponse<LoginResponse>>
    suspend fun register(registerRequest: RegisterRequest): Resource<BaseResponse<RegisterResponse>>
    suspend fun categoriesFlow(): Resource<BaseResponse<CategoryHomeResponse>>
    suspend fun listAllProductsByCategory(categoryId: Int): Resource<BaseResponse<GetAllProductResponse>>
}