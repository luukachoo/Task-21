package com.example.task21.data.local.mapper

import com.example.task21.data.local.model.ProductEntity
import com.example.task21.domain.remote.model.GetProduct

fun ProductEntity.toDomain() = GetProduct(
    cover,
    favorite,
    id,
    price,
    title,
)