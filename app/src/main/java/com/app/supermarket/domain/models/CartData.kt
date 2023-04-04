package com.app.supermarket.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CartData(
    val chargeAmount: Float,
    val discountAmount: Float,
    val id: Int,
    val products: List<Product>,
    val totalPrice: Float,
    val totalPriceAfterDiscount: Float
) : Parcelable