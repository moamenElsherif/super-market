package com.app.supermarket.data.models.response

class GetAllProductResponse(
    override val items: List<ProductResponse>?,
    override val totalCount: Int?
) : BaseItemsResponse<ProductResponse>