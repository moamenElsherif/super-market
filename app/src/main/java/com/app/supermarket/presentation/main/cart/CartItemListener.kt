package com.app.supermarket.presentation.main.cart

interface CartItemListener {
    fun clickDelete(productId: Int)
    fun clickCheckOut()
}