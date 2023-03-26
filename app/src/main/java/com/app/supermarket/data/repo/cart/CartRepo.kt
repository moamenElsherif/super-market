package com.app.supermarket.data.repo.cart

import com.app.supermarket.base.BaseResponse
import com.app.supermarket.base.Resource
import com.app.supermarket.data.models.cart.AddToCartRequest
import com.app.supermarket.data.models.cart.MyCartResponse

interface CartRepo {
    suspend fun addToCart(addToCartRequest: AddToCartRequest): Resource<BaseResponse<*>>
    suspend fun getMyCart(): Resource<BaseResponse<MyCartResponse>>
    suspend fun deleteFromCart(productId: Int): Resource<BaseResponse<*>>
}