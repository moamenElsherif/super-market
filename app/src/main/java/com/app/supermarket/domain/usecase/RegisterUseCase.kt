package com.app.supermarket.domain.usecase

import com.app.supermarket.base.BaseResponse
import com.app.supermarket.base.Resource
import com.app.supermarket.data.models.request.RegisterRequest
import com.app.supermarket.data.models.response.RegisterResponse
import com.app.supermarket.data.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RegisterUseCase  @Inject constructor(
    private val repo: Repository
) {

    operator fun invoke(registerRequest: RegisterRequest): Flow<Resource<BaseResponse<RegisterResponse>>> =
        flow {
            emit(Resource.Loading)
            emit(repo.register(registerRequest))
        }.flowOn(Dispatchers.IO)
}