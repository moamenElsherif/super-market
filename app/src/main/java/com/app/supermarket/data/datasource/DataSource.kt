package com.app.supermarket.data.datasource

import com.app.supermarket.base.BaseRemoteDataSource
import com.app.supermarket.data.api.ApiService
import com.app.supermarket.data.models.request.LoginRequest
import javax.inject.Inject

class DataSource @Inject constructor(
    private val apiService: ApiService
): BaseRemoteDataSource() {
    suspend fun login(loginRequest: LoginRequest)= safeApiCall {
        apiService.login(loginRequest)
    }
}