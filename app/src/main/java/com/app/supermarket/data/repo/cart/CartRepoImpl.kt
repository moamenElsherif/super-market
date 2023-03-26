package com.app.supermarket.data.repo.cart

import com.app.supermarket.base.BaseResponse
import com.app.supermarket.base.Resource
import com.app.supermarket.data.datasource.CartDataSource
import com.app.supermarket.data.models.cart.AddToCartRequest
import com.app.supermarket.data.models.cart.MyCartResponse
import javax.inject.Inject

class CartRepoImpl @Inject constructor(
    private val cartDataSource: CartDataSource
) : CartRepo {

    override suspend fun addToCart(addToCartRequest: AddToCartRequest): Resource<BaseResponse<*>> =
        cartDataSource.addToCart(addToCartRequest)

    override suspend fun getMyCart(): Resource<BaseResponse<MyCartResponse>> =
        cartDataSource.getMyCart()

    override suspend fun deleteFromCart(productId: Int): Resource<BaseResponse<*>> =
        cartDataSource.deleteFromCart(productId)

}