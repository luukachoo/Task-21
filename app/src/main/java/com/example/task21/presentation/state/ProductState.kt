package com.example.task21.presentation.state

import com.example.task21.presentation.model.Product

data class ProductState(
    val products: List<Product>? = emptyList(),
    val categories: List<String>? = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
