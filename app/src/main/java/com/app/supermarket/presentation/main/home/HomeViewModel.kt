package com.app.supermarket.presentation.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.supermarket.base.BaseResponse
import com.app.supermarket.base.Resource
import com.app.supermarket.data.models.response.CategoryHomeResponse
import com.app.supermarket.domain.usecase.CategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val categoryUseCase: CategoryUseCase,
) : ViewModel() {

    private val _categoryStateFlow = MutableStateFlow<Resource<BaseResponse<CategoryHomeResponse>>>(Resource.Default)
    val categoryStateFlow: StateFlow<Resource<BaseResponse<CategoryHomeResponse>>> = _categoryStateFlow

    init {
        getAllCategory()
    }

    private fun getAllCategory(){
        viewModelScope.launch {
            categoryUseCase.invoke().collectLatest {
                _categoryStateFlow.value = it
            }
        }
    }
}