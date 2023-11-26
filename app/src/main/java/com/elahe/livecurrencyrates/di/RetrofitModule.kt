package com.elahe.livecurrencyrates.di

import com.elahe.livecurrencyrates.data.remote.RateApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    private val baseRetrofit: Retrofit = createBaseNetworkClient()

    @Singleton
    @Provides
    fun provideRateApiService(): RateApiService {
        return baseRetrofit.create(RateApiService::class.java)
    }

}