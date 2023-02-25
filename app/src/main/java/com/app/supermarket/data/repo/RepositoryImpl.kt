package com.app.supermarket.data.repo

import com.app.supermarket.base.BaseResponse
import com.app.supermarket.base.Resource
import com.app.supermarket.data.datasource.DataSource
import com.app.supermarket.data.models.request.LoginRequest
import com.app.supermarket.data.models.response.LoginResponse
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dataSource: DataSource
) : Repository {

    override suspend fun login(
        loginRequest: LoginRequest
    ): Resource<BaseResponse<LoginResponse>> =
        dataSource.login(loginRequest)
}