package com.app.supermarket.presentation.main.home

import androidx.lifecycle.ViewModel
import com.app.supermarket.base.BaseResponse
import com.app.supermarket.base.Resource
import com.app.supermarket.data.models.response.HomeCategoryResponse
import com.app.supermarket.data.repo.category.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: CategoryRepository,
) : ViewModel() {

    val categoriesLiveData : Flow<Resource<BaseResponse<HomeCategoryResponse>>>
        get() = repository.categoriesFlow
}