package com.app.supermarket.data.models.response

data class Item(
    val attachmentFileId: Int,
    val description: String,
    val id: Int,
    val imageUrl: String,
    val isActive: Boolean,
    val localizedDescription: String,
    val localizedTitle: String,
    val title: String
)

data class CategoryHomeResponse(
    val items: List<Item>,
    val totalCount: Int
)