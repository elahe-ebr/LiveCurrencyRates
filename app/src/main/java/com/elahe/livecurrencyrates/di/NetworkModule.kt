package com.elahe.livecurrencyrates.di

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun createBaseNetworkClient() = retrofitClient()

fun retrofitClient(): Retrofit =
    Retrofit.Builder()
        .baseUrl("https://lokomond.com/")
        .client(getOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create(getGson()))
        .build()

fun getOkHttpClient(): OkHttpClient {

    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .addInterceptor {
            val oldRequest = it.request()
            val newRequestBuilder = oldRequest.newBuilder()
            newRequestBuilder.addHeader("Accept", "application/json")
            newRequestBuilder.method(oldRequest.method(), oldRequest.body())
            return@addInterceptor it.proceed(newRequestBuilder.build())
        }
    return okHttpClient.build()
}

private fun getGson() = GsonBuilder()
    .setLenient()
    .create()
