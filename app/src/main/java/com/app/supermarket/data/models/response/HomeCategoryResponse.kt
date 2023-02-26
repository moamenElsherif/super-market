package com.app.supermarket.data.models.response

data class HomeCategoryResponse(
    val items: List<CategoryResponse>,
    val totalCount: Int
)
