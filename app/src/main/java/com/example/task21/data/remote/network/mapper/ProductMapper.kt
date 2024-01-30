package com.example.task21.data.remote.network.mapper

import com.example.task21.data.local.model.ProductEntity
import com.example.task21.data.remote.network.model.ProductDto
import com.example.task21.domain.remote.model.GetProduct

fun ProductDto.toDomain() = GetProduct(
    cover = cover,
    favorite = favorite,
    id = id,
    price = price,
    title = title
)

fun ProductDto.toEntity() = ProductEntity(
    id, cover, favorite, price, title
)