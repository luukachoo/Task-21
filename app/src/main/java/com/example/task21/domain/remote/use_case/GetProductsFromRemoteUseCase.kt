package com.example.task21.domain.remote.use_case

import com.example.task21.data.remote.repository.RemoteRepository
import javax.inject.Inject

class GetProductsFromRemoteUseCase @Inject constructor(private val remoteRepository: RemoteRepository) {
    suspend operator fun invoke() =
        remoteRepository.getProducts()
}