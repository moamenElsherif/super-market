package com.app.supermarket.domain.usecase

import com.app.supermarket.base.BaseResponse
import com.app.supermarket.base.Resource
import com.app.supermarket.data.models.request.LoginRequest
import com.app.supermarket.data.models.response.LoginResponse
import com.app.supermarket.data.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repo: Repository,
    private val saveAuthTokenUseCase: SaveAuthTokenUseCase
) {

    operator fun invoke(loginRequest: LoginRequest): Flow<Resource<BaseResponse<LoginResponse>>> =
        flow {
            emit(Resource.Loading)
            val resource = repo.login(loginRequest)
            if (resource is Resource.Success) {
                val loginResponse : LoginResponse = resource.value.result
                saveAuthTokenUseCase(loginResponse)
            }
            emit(resource)
        }.flowOn(Dispatchers.IO)
}