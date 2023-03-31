package com.app.supermarket.data.models.response

data class ProductResponse(
    val id: Int?,
    val localizedName: String?,
    val name: String?,
    val isActive: Boolean?,
    val fileId: Int?,
    val description: String?,
    val localizedDescription: String?,
    val categoryId: Int?,
    val price: Float?,
    val priceAfterDiscount: Float?,
    val minCounter: Int?,
    val maxCounter: Int?,
    val imageUrl: String?,
    val stockCount: Int?
)


