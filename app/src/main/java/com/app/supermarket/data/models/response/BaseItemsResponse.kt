package com.app.supermarket.data.models.response

interface BaseItemsResponse<out O> {
    val items: List<O>?
    val totalCount: Int?
}