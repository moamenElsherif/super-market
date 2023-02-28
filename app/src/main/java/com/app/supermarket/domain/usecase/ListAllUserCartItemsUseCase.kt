package com.app.supermarket.domain.usecase

import com.app.supermarket.base.BaseResponse
import com.app.supermarket.base.Resource
import com.app.supermarket.data.models.response.AllCartItemsResponse
import com.app.supermarket.data.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ListAllUserCartItemsUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke() : Flow<Resource<BaseResponse<AllCartItemsResponse>>> = flow {
        emit(Resource.Loading)
        val result = repository.listAllUserCartProducts()
        emit(result)
    }.flowOn(Dispatchers.IO)
}