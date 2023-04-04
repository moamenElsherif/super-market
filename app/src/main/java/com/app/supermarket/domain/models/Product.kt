package com.app.supermarket.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
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
) : Parcelable