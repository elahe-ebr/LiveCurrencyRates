package com.elahe.livecurrencyrates.data.repository

import com.elahe.livecurrencyrates.util.calApi
import com.elahe.livecurrencyrates.data.remote.RateApiService
import javax.inject.Inject

class RateRepo @Inject constructor(private val apiService: RateApiService) {
    suspend fun getRates() = calApi {
        apiService.getRates()
    }
}