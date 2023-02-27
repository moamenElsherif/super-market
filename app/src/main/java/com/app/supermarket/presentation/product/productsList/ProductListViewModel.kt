package com.app.supermarket.presentation.product.productsList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.supermarket.base.BaseResponse
import com.app.supermarket.base.Resource
import com.app.supermarket.data.models.response.CategoryHomeResponse
import com.app.supermarket.data.models.response.GetAllProductResponse
import com.app.supermarket.domain.usecase.GetAllCategoryProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val getAllCategoryProductsUseCase: GetAllCategoryProductsUseCase
) : ViewModel() {

    private val _productsStateFlow = MutableStateFlow<Resource<BaseResponse<GetAllProductResponse>>>(Resource.Default)
    val productsStateFlow: StateFlow<Resource<BaseResponse<GetAllProductResponse>>>
        get() = _productsStateFlow

    fun getAllCategoryProductsById(categoryId: Int) {
        viewModelScope.launch {
            getAllCategoryProductsUseCase(categoryId).collectLatest { resource ->
                _productsStateFlow.value = resource
            }
        }
    }
}