package com.app.supermarket.presentation.checkout.checkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.supermarket.base.Resource
import com.app.supermarket.data.models.cart.Product
import com.app.supermarket.domain.usecase.cart.GetMyCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(
    private val getMyCartUseCase: GetMyCartUseCase
) : ViewModel() {

    private val _checkoutProducts : MutableLiveData<MutableList<Product>> = MutableLiveData()
    val checkoutProducts : LiveData<MutableList<Product>>
        get() = _checkoutProducts


    init {
        listCartItems()
    }

    private fun listCartItems() {
        viewModelScope.launch {
            getMyCartUseCase().collectLatest { resource ->
                if (resource is Resource.Success) {
                    val cartResponse = resource.value.result
                    _checkoutProducts.value = cartResponse.products.toMutableList()
                }
            }
        }
    }
}