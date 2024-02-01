package com.example.task21.domain.local.repository

import com.example.task21.domain.remote.model.GetProduct
import kotlinx.coroutines.flow.Flow

interface LocalProductRepository {
    fun getProducts(): Flow<List<GetProduct>>
    suspend fun insertProducts(products: List<GetProduct>)
}