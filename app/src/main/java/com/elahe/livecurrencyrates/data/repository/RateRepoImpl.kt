package com.elahe.livecurrencyrates.data.repository

import com.elahe.livecurrencyrates.util.calApi
import com.elahe.livecurrencyrates.data.remote.RateApiService

class RateRepoImpl(private val apiService: RateApiService) : RateRepo {

    override suspend fun getRates() = calApi {
        apiService.getRates()
    }
}