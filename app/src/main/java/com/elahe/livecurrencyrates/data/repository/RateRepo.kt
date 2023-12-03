package com.elahe.livecurrencyrates.data.repository

import com.elahe.livecurrencyrates.data.remote.RateApiService
import com.elahe.livecurrencyrates.util.Call
import javax.inject.Inject

class RateRepo @Inject constructor(private val apiService: RateApiService) : Call() {
    suspend fun getRates() = safeCallRemote {
        apiService.getRates()
    }
}