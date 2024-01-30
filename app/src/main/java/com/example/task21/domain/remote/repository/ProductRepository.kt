package com.example.task21.domain.remote.repository

import com.example.task21.data.remote.util.Resource
import com.example.task21.domain.remote.model.GetProduct
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProducts(): Flow<Resource<List<GetProduct>>>
}