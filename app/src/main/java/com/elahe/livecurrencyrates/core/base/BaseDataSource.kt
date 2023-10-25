package com.elahe.livecurrencyrates.core.base

import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.lang.Exception

suspend fun <T> calApi(call: suspend () -> Response<T>) = flow {
    emit(BaseApiDataState.Loading)
    try {
        val response = call()
        if (response.isSuccessful) {
            response.body()?.let {
                emit(BaseApiDataState.Success(it))
            }?: kotlin.run {
                emit(BaseApiDataState.Error("Error"))
            }
        }
        else
            emit(BaseApiDataState.Error("Error"))
    } catch (_: Exception) {
        emit(BaseApiDataState.Error("Error"))
    }
}
