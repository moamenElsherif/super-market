package com.app.supermarket.domain.usecase

import com.app.supermarket.base.BaseResponse
import com.app.supermarket.base.Resource
import com.app.supermarket.data.models.response.GetAllProductResponse
import com.app.supermarket.data.models.response.ProductResponse
import com.app.supermarket.data.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetProductUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(categoryId: Int): Flow<Resource<BaseResponse<ProductResponse>>> =
        flow {
            emit(Resource.Loading)
            emit(repository.getProduct(categoryId))
        }.flowOn(Dispatchers.IO)
}