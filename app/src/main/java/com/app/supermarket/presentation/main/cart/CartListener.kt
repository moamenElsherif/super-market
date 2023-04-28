package com.app.supermarket.presentation.main.cart

interface CartListener {
    fun clickDelete(productId: Int)
    fun clickCheckOut()
    fun clickSave(productId: Int, itemCount: Int)
    fun clickLogin()
    fun clickOpenCategories()
}