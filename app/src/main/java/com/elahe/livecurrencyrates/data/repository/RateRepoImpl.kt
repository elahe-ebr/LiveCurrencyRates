package com.elahe.livecurrencyrates.data.repository

import com.elahe.livecurrencyrates.core.base.calApi
import com.elahe.livecurrencyrates.data.api.RateApiService

class RateRepoImpl(private val apiService: RateApiService) : RateRepo {

    override suspend fun getRates() = calApi {
        apiService.getRates()
    }
}