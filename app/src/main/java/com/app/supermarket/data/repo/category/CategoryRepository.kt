package com.app.supermarket.data.repo.category

import com.app.supermarket.base.BaseResponse
import com.app.supermarket.base.Resource
import com.app.supermarket.data.models.response.HomeCategoryResponse
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    val categoriesFlow : Flow<Resource<BaseResponse<HomeCategoryResponse>>> }