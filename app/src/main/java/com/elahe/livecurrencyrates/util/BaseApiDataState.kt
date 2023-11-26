package com.elahe.livecurrencyrates.util

sealed class BaseApiDataState<out R> {
    object Loading : BaseApiDataState<Nothing>()
    data class Success<out T>(val data: T) : BaseApiDataState<T>()
    data class Error(val error: String) : BaseApiDataState<Nothing>()
}
