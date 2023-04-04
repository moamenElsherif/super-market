package com.app.supermarket.presentation.checkout.checkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.supermarket.domain.models.Checkout
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(

) : ViewModel() {
    private val _checkoutData: MutableLiveData<Checkout> = MutableLiveData()
    val checkoutData: LiveData<Checkout>
        get() = _checkoutData


    fun setCheckoutData(checkout: Checkout) {
        _checkoutData.value = checkout
    }

}