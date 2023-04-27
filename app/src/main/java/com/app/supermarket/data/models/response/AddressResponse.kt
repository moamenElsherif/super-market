package com.app.supermarket.data.models.response

import com.app.supermarket.presentation.main.settings.address.UserAddressUiState
import com.google.gson.annotations.SerializedName

data class AddressResponse(
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
) {
    fun toUserAddressUiState() = UserAddressUiState(userId, apartmentNum, buildingNum, street, addressNotes)
}
