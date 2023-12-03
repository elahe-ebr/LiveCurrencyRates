package com.elahe.livecurrencyrates.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okio.IOException
import retrofit2.Response

abstract class Call {
    suspend fun <T> safeCallRemote(callApi: suspend () -> Response<T>) = flow {
        emit(CallState.Loading)
        val response = callApi()
        if (response.isSuccessful) {
            response.body()?.let {
                emit(CallState.Success(it))
            } ?: kotlin.run {
                emit(
                    CallState.Error(
                        IOException(
                            response.errorBody()?.string() ?: "Unknown error"
                        )
                    )
                )
                return@flow
            }
        } else {
            emit(CallState.Error(Throwable(response.errorBody()?.string())))
            return@flow
        }

    }.catch { e ->
        emit(CallState.Error(Exception(e)))
    }.flowOn(Dispatchers.IO)
}

