package com.app.supermarket.domain.usecase

import com.app.supermarket.base.BaseResponse
import com.app.supermarket.base.Resource
import com.app.supermarket.data.models.response.CategoryHomeResponse
import com.app.supermarket.data.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CategoryUseCase @Inject constructor(
    private val repo: Repository
) {

    operator fun invoke(): Flow<Resource<BaseResponse<CategoryHomeResponse>>> =
        flow {
            emit(Resource.Loading)
            emit(repo.categoriesFlow())
        }.flowOn(Dispatchers.IO)
}