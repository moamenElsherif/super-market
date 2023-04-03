package com.app.supermarket.data.models.cart

data class MyCartResponse(
    val chargeAmount: Float,
    val discountAmount: Float,
    val id: Int,
    val products: List<Product>,
    val totalPrice: Float,
    val totalPriceAfterDiscount: Float
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
    val price: Float,
    val priceAfterDiscount: Float,
    val productId: Int,
    val productName: String,
    val quantity: Int,
    val stockCount: Int
)