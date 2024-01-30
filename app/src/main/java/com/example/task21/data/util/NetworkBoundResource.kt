package com.example.task21.data.util

import com.example.task21.data.remote.util.Resource
import com.example.task21.domain.remote.model.GetProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

inline fun <ResultType : Any, RequestType : Any> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline mapToResult: (ResultType) -> List<GetProduct>,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        try {
            saveFetchResult(fetch())
            query().map { Resource.Success(mapToResult(it)) }
        } catch (throwable: Throwable) {
            query().map { Resource.Error(throwable.message!!, mapToResult(it)) }
        }
    } else {
        query().map { Resource.Success(mapToResult(it)) }
    }
    emitAll(flow)
}
