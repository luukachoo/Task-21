package com.example.task21.data.remote.network.mapper

import com.example.task21.data.remote.network.model.ProductDto
import com.example.task21.domain.remote.model.GetProduct

fun ProductDto.toDomain() = GetProduct(
    cover = cover,
    favorite = favorite,
    id = id,
    price = price,
    title = title,
    category = category
)