package com.app.supermarket.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Checkout(
    val addressId: Int,
    val cart: CartData
) : Parcelable
