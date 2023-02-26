package com.app.supermarket.data.api.category


import com.app.supermarket.base.BaseResponse
import com.app.supermarket.base.Constants
import com.app.supermarket.data.models.response.HomeCategoryResponse
import retrofit2.http.GET

interface CategoryApiService {
    @GET(Constants.GET_ALL_CATEGORIES_URL)
    suspend fun listHomeCategories() : BaseResponse<HomeCategoryResponse>
}