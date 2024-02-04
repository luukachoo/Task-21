package com.example.task21.data.remote.service

import com.example.task21.data.remote.network.model.ProductDto
import retrofit2.Response
import retrofit2.http.GET

interface GetProductsService {
    @GET("df8d4951-2757-45aa-8f60-bf1592a090ce")
    suspend fun getProducts(): Response<List<ProductDto>>
}