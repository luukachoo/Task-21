package com.example.task21.domain.remote.use_case

import com.example.task21.domain.remote.repository.ProductRepository
import javax.inject.Inject

class GetProductUseCase @Inject constructor(private val repository: ProductRepository) {
    suspend operator fun invoke() =
        repository.getProducts()
}