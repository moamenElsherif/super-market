package com.app.supermarket.presentation.product.productdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.supermarket.base.BaseResponse
import com.app.supermarket.base.Resource
import com.app.supermarket.data.models.response.GetAllProductResponse
import com.app.supermarket.data.models.response.ProductResponse
import com.app.supermarket.domain.usecase.GetProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase
): ViewModel() {

    private val _productResponse = MutableStateFlow<Resource<BaseResponse<ProductResponse>>>(Resource.Default)
    val productResponse: StateFlow<Resource<BaseResponse<ProductResponse>>> = _productResponse

    fun getProducts(categoryId: Int){
        viewModelScope.launch {
            getProductUseCase.invoke(categoryId).collectLatest{
                _productResponse.value = it
            }
        }
    }
}