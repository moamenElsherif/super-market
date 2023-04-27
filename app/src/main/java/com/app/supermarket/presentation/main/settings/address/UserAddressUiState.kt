package com.app.supermarket.presentation.main.settings.address

import com.app.supermarket.data.models.request.AddressRequest


data class UserAddressUiState(
    val userId: Int = 0,
    val apartmentNum : String = "",
    val buildingNum : String = "",
    val street : String = "",
    val addressNotes : String = "",
){
    fun toAddressRequest() = AddressRequest(userId = userId, apartmentNum = apartmentNum, buildingNum = buildingNum, street = street, addressNotes = addressNotes)
}
