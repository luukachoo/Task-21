package com.example.task21.data.remote.repository

import com.example.task21.data.remote.network.mapper.asResource
import com.example.task21.data.remote.network.mapper.toDomain
import com.example.task21.data.remote.service.GetProductsService
import com.example.task21.data.remote.util.Resource
import com.example.task21.data.remote.util.ResponseHandler
import com.example.task21.domain.remote.model.GetProduct
import com.example.task21.domain.remote.repository.RemoteProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val service: GetProductsService,
    private val responseHandler: ResponseHandler
) : RemoteProductRepository {
    override suspend fun getProducts(): Flow<Resource<List<GetProduct>>> {
        return responseHandler.handleApiCall {
            service.getProducts()
        }.asResource { list ->
            list.map { dto ->
                dto.toDomain()
            }
        }
    }
}