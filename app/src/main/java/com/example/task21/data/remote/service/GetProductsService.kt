package com.example.task21.data.remote.service

import com.example.task21.data.remote.network.model.ProductDto
import retrofit2.Response
import retrofit2.http.GET

interface GetProductsService {
    @GET("1775d634-92dc-4c32-ae71-1707b8cfee41")
    suspend fun getProducts(): Response<List<ProductDto>>
}