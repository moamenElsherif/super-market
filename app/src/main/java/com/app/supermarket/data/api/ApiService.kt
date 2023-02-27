package com.app.supermarket.data.api

import com.app.supermarket.base.BaseResponse
import com.app.supermarket.base.Constants
import com.app.supermarket.data.models.request.LoginRequest
import com.app.supermarket.data.models.request.RegisterRequest
import com.app.supermarket.data.models.response.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST(LOGIN)
    suspend fun login(@Body request: LoginRequest): BaseResponse<LoginResponse>

    @POST(REGISTER)
    suspend fun register(@Body request: RegisterRequest): BaseResponse<RegisterResponse>

    @GET(GET_ALL_CATEGORIES_URL)
    suspend fun listHomeCategories() : BaseResponse<CategoryHomeResponse>

    @GET(GET_ALL_PRODUCTS_BY_CATEGORY_URL)
    suspend fun listAllProductsByCategory(@Query(value = "CategoryId") categoryId: Int) : BaseResponse<GetAllProductResponse>

    @GET(GET_PRODUCT)
    suspend fun getProduct(@Query(value = "id") categoryId: Int) : BaseResponse<ProductResponse>




    companion object {

        // Get All Categories URL
        const val GET_ALL_CATEGORIES_URL = "api/services/app/Category/GetAll"
        // Get All Products by Category Id
        const val GET_ALL_PRODUCTS_BY_CATEGORY_URL = "api/services/app/Product/GetAll"

        const val REGISTER = "/api/services/app/Account/RegisterV2"

        const val LOGIN = "/api/TokenAuth/AuthenticateCustomer"

        const val GET_PRODUCT = "/api/services/app/Product/Get"
    }

}