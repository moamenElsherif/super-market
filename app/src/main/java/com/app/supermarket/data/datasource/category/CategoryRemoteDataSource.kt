package com.app.supermarket.data.datasource.category

import com.app.supermarket.base.BaseRemoteDataSource
import com.app.supermarket.data.api.category.CategoryApiService
import javax.inject.Inject

class CategoryRemoteDataSource @Inject constructor(
    private val categoryApiService: CategoryApiService
) : BaseRemoteDataSource() {

    suspend fun listHomeCategories() = safeApiCall {
        categoryApiService.listHomeCategories()
    }
}