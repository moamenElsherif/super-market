package com.app.supermarket.presentation.main.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.supermarket.base.BaseResponse
import com.app.supermarket.base.Resource
import com.app.supermarket.data.models.cart.MyCartResponse
import com.app.supermarket.domain.usecase.ListAllUserCartItemsUseCase
import com.app.supermarket.domain.usecase.cart.AddToCartUseCase
import com.app.supermarket.domain.usecase.cart.DeleteItemFromCartUseCase
import com.app.supermarket.domain.usecase.cart.GetMyCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getMyCartUseCase: GetMyCartUseCase,
    private val deleteItemFromCartUseCase: DeleteItemFromCartUseCase,
    private val addToCartUseCase: AddToCartUseCase
) : ViewModel() {

    private var _cartResponse = MutableStateFlow<Resource<BaseResponse<MyCartResponse>>>(Resource.Loading)
    val cartResponse: StateFlow<Resource<BaseResponse<MyCartResponse>>> = _cartResponse

    private val _updateCartResponse =
        MutableStateFlow<Resource<BaseResponse<*>>>(Resource.Default)
    val updateCartResponse: StateFlow<Resource<BaseResponse<*>>> = _updateCartResponse


    init {
        getMyCart()
    }

    fun getMyCart(){
        viewModelScope.launch {
            getMyCartUseCase.invoke().collectLatest {
                _cartResponse.value = it
            }
        }
    }

    fun updateCart(productId: Int, quantity: Int) {
        viewModelScope.launch {
            _cartResponse.value = Resource.Loading
            addToCartUseCase.invoke(
                productId,
                quantity
            ).collect {
                _updateCartResponse.value = it
            }
        }
    }

    fun deleteFromCart(productId: Int) {
        viewModelScope.launch {
            deleteItemFromCartUseCase.invoke(
                productId,
            ).collectLatest {
                _updateCartResponse.value = it
            }
        }
    }

}