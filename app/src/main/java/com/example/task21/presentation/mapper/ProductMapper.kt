package com.example.task21.presentation.mapper

import com.example.task21.domain.remote.model.GetProduct
import com.example.task21.presentation.model.Product

fun GetProduct.toPresentation() = Product(
    cover = cover,
    favorite = favorite,
    id = id,
    price = price,
    title = title
)