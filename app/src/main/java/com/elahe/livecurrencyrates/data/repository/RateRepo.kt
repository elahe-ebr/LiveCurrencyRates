package com.elahe.livecurrencyrates.data.repository


import com.elahe.livecurrencyrates.util.BaseApiDataState
import com.elahe.livecurrencyrates.data.model.ResponseModel
import kotlinx.coroutines.flow.Flow

interface RateRepo {
    suspend fun getRates(): Flow<BaseApiDataState<ResponseModel>>
}