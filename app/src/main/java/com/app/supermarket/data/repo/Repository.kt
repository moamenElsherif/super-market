package com.app.supermarket.data.repo

import com.app.supermarket.base.BaseResponse
import com.app.supermarket.base.Resource
import com.app.supermarket.data.models.request.AddressRequest
import com.app.supermarket.data.models.request.LoginRequest
import com.app.supermarket.data.models.request.RegisterRequest
import com.app.supermarket.data.models.response.*

interface Repository {
    suspend fun login(loginRequest: LoginRequest): Resource<BaseResponse<LoginResponse>>
    suspend fun register(registerRequest: RegisterRequest): Resource<BaseResponse<RegisterResponse>>
    suspend fun categoriesFlow(): Resource<BaseResponse<CategoryHomeResponse>>
    suspend fun listAllProductsByCategory(categoryId: Int): Resource<BaseResponse<GetAllProductResponse>>
    suspend fun getProduct(categoryId: Int): Resource<BaseResponse<ProductResponse>>
    suspend fun getUserData(userId: Int): Resource<BaseResponse<UserDataResponse>>

    suspend fun listAllUserCartProducts(): Resource<BaseResponse<AllCartItemsResponse>>

    suspend fun getUserAddress(userId: Int): Resource<BaseResponse<AddressResponse>>

    suspend fun addUserAddress(addressRequest: AddressRequest): Resource<BaseResponse<AddressResponse>>
}