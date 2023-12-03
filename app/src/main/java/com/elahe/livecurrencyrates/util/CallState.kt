package com.elahe.livecurrencyrates.util

sealed class CallState<out R> {
    object Loading : CallState<Nothing>()
    data class Success<out T>(val data: T) : CallState<T>()
    data class Error(val e: Throwable) : CallState<Nothing>()
}
