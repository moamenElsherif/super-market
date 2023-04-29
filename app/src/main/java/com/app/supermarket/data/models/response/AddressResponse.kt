package com.app.supermarket.data.models.response

import com.app.supermarket.presentation.address.UserAddressUiState
import com.google.gson.annotations.SerializedName

data class AddressResponse(
    @SerializedName("id")
    val addressId: Int,
    val latitude : Int = 0,
    @SerializedName("longtude")
    val longitude : Int = 0,
    @SerializedName("appartmentNum")
    val apartmentNum : String,
    val buildingNum : String,
    val street : String,
    val addressNotes : String,
) {
    fun toUserAddressUiState() = UserAddressUiState(addressId, apartmentNum, buildingNum, street, addressNotes)
}
