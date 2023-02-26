package com.app.supermarket.presentation.main.home

import com.app.supermarket.data.models.response.CategoryResponse

sealed class HomeEvents {
    object Loading : HomeEvents()
    data class Data(val categories: CategoryResponse) : HomeEvents()
    data class Error(val message: String) : HomeEvents()
}