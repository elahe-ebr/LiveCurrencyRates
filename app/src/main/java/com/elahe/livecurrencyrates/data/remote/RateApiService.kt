package com.elahe.livecurrencyrates.data.remote

import com.elahe.livecurrencyrates.data.model.ResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface RateApiService {

    @GET("code-challenge/index.php")
    suspend fun getRates():Response<ResponseModel>

}