package com.example.task21.domain.repository

import com.example.task21.data.remote.util.Resource
import com.example.task21.domain.remote.model.GetProduct
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun checkConnectionAndGetProducts(): Flow<Resource<List<GetProduct>>>
}