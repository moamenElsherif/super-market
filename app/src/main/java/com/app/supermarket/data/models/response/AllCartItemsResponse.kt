package com.app.supermarket.data.models.response


data class AllCartItemsResponse(
    override val items: List<CartItemResponse>?,
    override val totalCount: Int?
) : BaseItemsResponse<CartItemResponse>
