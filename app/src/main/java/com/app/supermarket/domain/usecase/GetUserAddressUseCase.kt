package com.app.supermarket.domain.usecase

import com.app.supermarket.base.BaseResponse
import com.app.supermarket.base.Resource
import com.app.supermarket.data.models.response.AddressResponse
import com.app.supermarket.data.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetUserAddressUseCase@Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(userId: Int): Flow<Resource<BaseResponse<AddressResponse>>> =
        flow {
            emit(Resource.Loading)
            emit(repository.getUserAddress(userId))
        }.flowOn(Dispatchers.IO)
}