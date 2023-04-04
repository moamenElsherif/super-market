package com.app.supermarket.presentation.product.productdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.supermarket.base.BaseResponse
import com.app.supermarket.base.Resource
import com.app.supermarket.data.models.cart.AddToCartRequest
import com.app.supermarket.data.models.response.ProductResponse
import com.app.supermarket.domain.usecase.GetProductUseCase
import com.app.supermarket.domain.usecase.cart.AddToCartUseCase
import com.app.supermarket.domain.usecase.cart.DeleteItemFromCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase,
    private val addToCartUseCase: AddToCartUseCase,
) : ViewModel() {

    private val _productResponse =
        MutableStateFlow<Resource<BaseResponse<ProductResponse>>>(Resource.Loading)
    val productResponse: StateFlow<Resource<BaseResponse<ProductResponse>>> = _productResponse

    private val _cartResponse =
        MutableStateFlow<Resource<BaseResponse<*>>>(Resource.Default)
    val cartResponse: StateFlow<Resource<BaseResponse<*>>> = _cartResponse

    fun getProducts(categoryId: Int) {
        viewModelScope.launch {
            getProductUseCase.invoke(categoryId).collectLatest {
                _productResponse.value = it
            }
        }
    }

    fun addToCart(productId: Int, quantity: Int) {
        viewModelScope.launch {
            _cartResponse.value = Resource.Loading
            addToCartUseCase.invoke(
                productId,
                quantity
            ).collect {
                _cartResponse.value = it
            }
        }
    }
}