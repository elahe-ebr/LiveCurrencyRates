package com.elahe.livecurrencyrates.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

suspend fun <T> calApi(call: suspend () -> Response<T>) = flow {
    emit(BaseApiDataState.Loading)
    val response = call()
    if (response.isSuccessful) {
        response.body()?.let {
            emit(BaseApiDataState.Success(it))
        } ?: kotlin.run {
            emit(BaseApiDataState.Error("Error communicating with the server"))
        }
    } else
        emit(BaseApiDataState.Error("Error communicating with the server"))

}.catch { e ->
    emit(BaseApiDataState.Error(e.message ?: "Unknown Error"))
}.flowOn(Dispatchers.IO)
