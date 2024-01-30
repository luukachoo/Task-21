package com.example.task21.data.remote.network.mapper

import com.example.task21.data.remote.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

suspend fun <Dto : Any, Domain : Any> Flow<Resource<Dto>>.asResource(
    onSuccess: suspend (Dto) -> Domain,
): Flow<Resource<Domain>> {
    return this.map {
        when (it) {
            is Resource.Success -> Resource.Success(data = onSuccess.invoke(it.data))
            is Resource.Error -> Resource.Error(errorMessage = it.errorMessage)
        }
    }
}