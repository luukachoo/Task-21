package com.example.task21.domain.remote.use_case

import javax.inject.Inject

data class ProductsUseCase @Inject constructor(
    val getProductsFromRemote: GetProductsFromRemoteUseCase,
    val getProductsFromDatabase: GetProductsFromDatabseUseCase,
    val getProductByCategory: GetProductByCategoryUseCase,
    val insertProductsToDatabase: InsertProductsToDatabaseUseCase,
)
