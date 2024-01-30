package com.example.task21.data.repository

import com.example.task21.data.local.dao.ProductDao
import com.example.task21.data.local.mapper.toDomain
import com.example.task21.data.remote.network.mapper.toEntity
import com.example.task21.data.remote.service.GetProductsService
import com.example.task21.data.remote.util.Resource
import com.example.task21.data.util.NetworkStatusTracker
import com.example.task21.data.util.networkBoundResource
import com.example.task21.domain.remote.model.GetProduct
import com.example.task21.domain.remote.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CommonDataSource @Inject constructor(
    private val productDao: ProductDao,
    private val service: GetProductsService,
    private val networkStatusTracker: NetworkStatusTracker
) : ProductRepository {
    override suspend fun getProducts(): Flow<Resource<List<GetProduct>>> = networkBoundResource(
        query = {
            productDao.getProducts()
        },

        fetch = {
            service.getProducts()
        },

        saveFetchResult = { response ->
            response.body()?.let { productList ->
                val getProductList = productList.map { item ->
                    item.toEntity()
                }
                productDao.insertProducts(getProductList)
            }
        },

        mapToResult = { result ->
            result.map { it.toDomain() }
        },

        shouldFetch = { items ->
            networkStatusTracker.isConnected() || items.isEmpty()
        }

    )
}

