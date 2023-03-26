package com.app.supermarket.data.api

import com.app.supermarket.base.BaseResponse
import com.app.supermarket.data.models.cart.AddToCartRequest
import com.app.supermarket.data.models.cart.MyCartResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface CartApiService {

    @POST(ADD_TO_CART)
    suspend fun addToCart(@Body addToCartRequest: AddToCartRequest): BaseResponse<*>

    @POST(GET_MY_CART)
    suspend fun getMyCart(): BaseResponse<MyCartResponse>

    @POST(DELETE_ITEM_FROM_CART)
    suspend fun deleteFromCart(@Query(value = "productId") productId: Int): BaseResponse<*>

    companion object {
        const val ADD_TO_CART = "/api/services/app/Cart/AddToCart"
        const val GET_MY_CART = "/api/services/app/Cart/MyCart"
        const val DELETE_ITEM_FROM_CART = "/api/services/app/Cart/DeletItemFromMyCart"
    }
}