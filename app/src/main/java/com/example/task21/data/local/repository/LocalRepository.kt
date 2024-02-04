package com.example.task21.data.local.repository

import com.example.task21.data.local.dao.ProductDao
import com.example.task21.data.local.mapper.asEntity
import com.example.task21.data.local.mapper.toDomain
import com.example.task21.domain.local.repository.LocalProductRepository
import com.example.task21.domain.remote.model.GetProduct
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalRepository @Inject constructor(private val productDao: ProductDao) :
    LocalProductRepository {
    override fun getProducts(): Flow<List<GetProduct>> {
        return productDao.getProducts().map { list ->
            list.map { productEntity ->
                productEntity.toDomain()
            }
        }
    }

    override suspend fun insertProducts(products: List<GetProduct>) = withContext(Dispatchers.IO) {
        productDao.insertProducts(products.map { it.asEntity() })
    }

    override fun getProductByCategory(category: String): Flow<List<GetProduct>> {
        return productDao.getProductsByCategory(category).map { list ->
            list.map { productEntity ->
                productEntity.toDomain()
            }
        }
    }
}