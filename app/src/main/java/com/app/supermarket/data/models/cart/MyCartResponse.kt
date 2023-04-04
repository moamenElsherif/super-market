package com.app.supermarket.data.models.cart

import android.os.Parcelable
import com.app.supermarket.domain.models.CartData
import com.app.supermarket.domain.models.Checkout
import com.app.supermarket.domain.models.Product

data class MyCartResponse(
    val chargeAmount: Float,
    val discountAmount: Float,
    val id: Int,
    val products: List<ProductResponse>,
    val totalPrice: Float,
    val totalPriceAfterDiscount: Float
) {
    fun toCartData() : CartData = CartData(chargeAmount, discountAmount, id, products.map(ProductResponse::toProduct), totalPrice, totalPriceAfterDiscount)
}

data class ProductResponse(
    val categoryId: Int,
    val description: String,
    val imageUrl: String,
    val isActive: Boolean,
    val localizedDescription: String?,
    val localizedName: String,
    val maxCounter: Int,
    val minCounter: Int,
    val price: Float,
    val priceAfterDiscount: Float,
    val productId: Int,
    val productName: String,
    val quantity: Int,
    val stockCount: Int
) {
    fun toProduct(): Product = Product(
        categoryId,
        description,
        imageUrl,
        isActive,
        localizedDescription,
        localizedName,
        maxCounter,
        minCounter,
        price,
        priceAfterDiscount,
        productId,
        productName,
        quantity,
        stockCount
    )
}