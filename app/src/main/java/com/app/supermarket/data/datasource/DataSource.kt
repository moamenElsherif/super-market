package com.app.supermarket.data.datasource

import com.app.supermarket.base.BaseRemoteDataSource
import com.app.supermarket.data.api.ApiService
import com.app.supermarket.data.models.request.LoginRequest
import com.app.supermarket.data.models.request.RegisterRequest
import javax.inject.Inject

class DataSource @Inject constructor(
    private val apiService: ApiService
): BaseRemoteDataSource() {
    suspend fun login(loginRequest: LoginRequest) = safeApiCall {
        apiService.login(loginRequest)
    }

    suspend fun register(registerRequest: RegisterRequest)= safeApiCall {
        apiService.register(registerRequest)
    }

    suspend fun listHomeCategories() = safeApiCall {
        apiService.listHomeCategories()
    }

    suspend fun listAllProductsByCategory(categoryId: Int) = safeApiCall {
        apiService.listAllProductsByCategory(categoryId)
    }

    suspend fun getProduct(categoryId: Int) = safeApiCall {
        apiService.getProduct(categoryId)
    }

    suspend fun listAllUserCartProducts() = safeApiCall {
        apiService.listAllUserCartProducts()
    }
}