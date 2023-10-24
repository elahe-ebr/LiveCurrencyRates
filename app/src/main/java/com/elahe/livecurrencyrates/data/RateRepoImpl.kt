package com.elahe.livecurrencyrates.data

import com.elahe.livecurrencyrates.core.base.BaseApiDataState
import com.elahe.livecurrencyrates.core.base.calApi
import com.elahe.livecurrencyrates.api.RateApiService
import com.elahe.livecurrencyrates.data.model.ResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RateRepoImpl(private val apiService: RateApiService) : RateRepo {

    override suspend fun getRates(): Flow<BaseApiDataState<ResponseModel>> = flow {
        calApi {
            apiService.getRates()
        }
    }
}