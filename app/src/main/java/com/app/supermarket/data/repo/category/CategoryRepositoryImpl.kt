package com.app.supermarket.data.repo.category

import com.app.supermarket.base.BaseResponse
import com.app.supermarket.base.Resource
import com.app.supermarket.data.datasource.category.CategoryRemoteDataSource
import com.app.supermarket.data.models.response.HomeCategoryResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryRemoteDataSource: CategoryRemoteDataSource
) :  CategoryRepository {

    override val categoriesFlow : Flow<Resource<BaseResponse<HomeCategoryResponse>>> = flow {
        emit(Resource.Loading)
        val resource = categoryRemoteDataSource.listHomeCategories()
        emit(resource)
    }.flowOn(Dispatchers.IO)

}