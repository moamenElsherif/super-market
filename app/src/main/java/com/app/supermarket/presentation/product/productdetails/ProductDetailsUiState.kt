package com.app.supermarket.presentation.product.productdetails

data class ProductDetailsUiState (
    val id: Int? = null,
    val localizedName: String? = null,
    val isActive: Boolean? = null,
    val description: String? = null,
    val localizedDescription: String? = null,
    val categoryId: Int? = null,
    val price: Float? = null,
    val priceAfterDiscount: Float? = null,
    val minCounter: Int? = null,
    val maxCounter: Int? = null,
    val imageUrl: String? = null,
    val stockCount: Int? = null

){
    fun getProductPrice():String{
        return price.toString()
    }
}