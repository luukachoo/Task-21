package com.example.task21.domain.remote.use_case

import com.example.task21.data.local.repository.LocalRepository
import com.example.task21.domain.remote.model.GetProduct
import javax.inject.Inject

class InsertProductsToDatabaseUseCase @Inject constructor(private val localRepository: LocalRepository) {
    suspend operator fun invoke(productsList: List<GetProduct>) =
        localRepository.insertProducts(products = productsList)
}