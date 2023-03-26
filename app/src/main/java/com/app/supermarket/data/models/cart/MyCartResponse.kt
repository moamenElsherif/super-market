package com.app.supermarket.data.models.cart

data class MyCartResponse(
    val chargeAmount: Int,
    val discountAmount: Int,
    val id: Int,
    val products: List<Product>,
    val totalPrice: Int,
    val totalPriceAfterDiscount: Int
)

data class Product(
    val categoryId: Int,
    val description: String,
    val imageUrl: String,
    val isActive: Boolean,
    val localizedDescription: String,
    val localizedName: String,
    val maxCounter: Int,
    val minCounter: Int,
    val price: Int,
    val priceAfterDiscount: Int,
    val productId: Int,
    val productName: String,
    val quantity: Int,
    val stockCount: Int
)