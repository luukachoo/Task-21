package com.example.task21.data.remote.network.model

import com.squareup.moshi.Json

data class ProductDto(
    @Json(name = "cover")
    val cover: String,
    @Json(name = "favorite")
    val favorite: Boolean,
    @Json(name = "id")
    val id: Int,
    @Json(name = "price")
    val price: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "category")
    val category: String
)