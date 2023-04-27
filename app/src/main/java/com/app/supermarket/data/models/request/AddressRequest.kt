package com.app.supermarket.data.models.request

import com.google.gson.annotations.SerializedName

data class AddressRequest(
    @SerializedName("id")
    val userId: Int,
    val latitude : Int = 0,
    @SerializedName("longtude")
    val longitude : Int = 0,
    @SerializedName("appartmentNum")
    val apartmentNum : String,
    val buildingNum : String,
    val street : String,
    val addressNotes : String,
)
