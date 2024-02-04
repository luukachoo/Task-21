package com.example.task21.domain.remote.use_case

import com.example.task21.data.local.repository.LocalRepository
import javax.inject.Inject

class GetProductsFromDatabseUseCase @Inject constructor(private val localRepository: LocalRepository) {
    operator fun invoke() =
        localRepository.getProducts()
}