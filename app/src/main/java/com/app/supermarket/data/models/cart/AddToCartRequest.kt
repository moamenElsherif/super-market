package com.app.supermarket.data.models.cart

data class AddToCartRequest(
    val productId: Int,
    val qty: Int
)