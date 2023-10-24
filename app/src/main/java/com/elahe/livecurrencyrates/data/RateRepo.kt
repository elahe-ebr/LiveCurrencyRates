package com.elahe.livecurrencyrates.data


import com.elahe.livecurrencyrates.core.base.BaseApiDataState
import com.elahe.livecurrencyrates.data.model.ResponseModel
import kotlinx.coroutines.flow.Flow

interface RateRepo {
    suspend fun getRates(): Flow<BaseApiDataState<ResponseModel>>
}