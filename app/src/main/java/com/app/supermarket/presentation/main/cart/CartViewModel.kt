package com.app.supermarket.presentation.main.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.supermarket.base.BaseResponse
import com.app.supermarket.base.Resource
import com.app.supermarket.data.models.cart.MyCartResponse
import com.app.supermarket.domain.usecase.ListAllUserCartItemsUseCase
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
    private val deleteItemFromCartUseCase: DeleteItemFromCartUseCase
) : ViewModel() {

    private var _cartResponse = MutableStateFlow<Resource<BaseResponse<MyCartResponse>>>(Resource.Loading)
    val cartResponse: StateFlow<Resource<BaseResponse<MyCartResponse>>> = _cartResponse

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

    fun deleteFromCart(productId: Int) {
        viewModelScope.launch {
            deleteItemFromCartUseCase.invoke(
                productId,
            ).collectLatest {

            }
        }
    }

}