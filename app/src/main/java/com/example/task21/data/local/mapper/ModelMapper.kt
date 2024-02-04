package com.example.task21.data.local.mapper

import com.example.task21.data.local.model.ProductEntity
import com.example.task21.domain.remote.model.GetProduct

fun ProductEntity.toDomain() = GetProduct(
    cover = cover,
    favorite = favorite,
    id = id,
    price = price,
    title = title,
    category = category!!
)

fun GetProduct.asEntity() = ProductEntity(
    id = id,
    cover = cover,
    favorite = favorite,
    price = price,
    title = title,
    category = category
)