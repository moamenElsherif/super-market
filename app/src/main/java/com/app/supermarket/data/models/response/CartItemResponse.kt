package com.app.supermarket.data.models.response

data class CartItemResponse(
    val id: Int?,
    val totalPrice: Float?,
    val totalPriceAfterDiscount: Float?,
    val status: Int?,
    val itemCount: Int?,
    val productResponse: ProductResponse?
)
