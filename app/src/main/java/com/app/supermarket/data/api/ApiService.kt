package com.app.supermarket.data.api

import com.app.supermarket.base.BaseResponse
import com.app.supermarket.base.Constants
import com.app.supermarket.data.models.request.LoginRequest
import com.app.supermarket.data.models.request.RegisterRequest
import com.app.supermarket.data.models.response.CategoryHomeResponse
import com.app.supermarket.data.models.response.LoginResponse
import com.app.supermarket.data.models.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("/api/TokenAuth/AuthenticateCustomer")
    suspend fun login(@Body request: LoginRequest): BaseResponse<LoginResponse>

    @POST("/api/services/app/Account/RegisterV2")
    suspend fun register(@Body request: RegisterRequest): BaseResponse<RegisterResponse>

    @GET(Constants.GET_ALL_CATEGORIES_URL)
    suspend fun listHomeCategories() : BaseResponse<CategoryHomeResponse>

}