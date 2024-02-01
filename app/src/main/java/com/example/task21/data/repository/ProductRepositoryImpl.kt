package com.example.task21.data.repository

import com.example.task21.data.remote.util.Resource
import com.example.task21.data.util.ConnectivityStatusChecker
import com.example.task21.domain.local.repository.LocalProductRepository
import com.example.task21.domain.remote.model.GetProduct
import com.example.task21.domain.remote.repository.RemoteProductRepository
import com.example.task21.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val remoteProductRepository: RemoteProductRepository,
    private val localRepository: LocalProductRepository,
    private val connectivityStatusChecker: ConnectivityStatusChecker
) : ProductRepository {
    override suspend fun checkConnectionAndGetProducts(): Flow<Resource<List<GetProduct>>> = flow {
        connectivityStatusChecker.isConnected.collect { isConnected ->
            if (isConnected) {
                remoteProductRepository.getProducts().collect { res ->
                    when (res) {
                        is Resource.Success -> {
                            localRepository.insertProducts(res.data)
                            emit(res)
                        }

                        is Resource.Error -> emit(res)
                    }
                }
            } else {
                localRepository.getProducts().collect { listOfProducts ->
                    emit(Resource.Success(data = listOfProducts))
                }
            }
        }
    }
}