package com.app.supermarket.data.datasource

import com.app.supermarket.base.BaseRemoteDataSource
import com.app.supermarket.data.api.CartApiService
import com.app.supermarket.data.models.cart.AddToCartRequest
import javax.inject.Inject

class CartDataSource @Inject constructor(
    private val cartApiService: CartApiService
) : BaseRemoteDataSource() {

    suspend fun addToCart(addToCartRequest: AddToCartRequest) = safeApiCall {
        cartApiService.addToCart(addToCartRequest)
    }

    suspend fun getMyCart() = safeApiCall {
        cartApiService.getMyCart()
    }

    suspend fun deleteFromCart(productId: Int) = safeApiCall {
        cartApiService.deleteFromCart(productId)
    }

}