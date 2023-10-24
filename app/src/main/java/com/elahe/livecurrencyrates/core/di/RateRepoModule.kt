package com.elahe.livecurrencyrates.core.di

import com.elahe.livecurrencyrates.data.RateRepoImpl
import com.elahe.livecurrencyrates.api.RateApiService
import com.elahe.livecurrencyrates.data.RateRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RateRepoModule() {

    @Singleton
    @Provides
    fun provideRatesRepository(apiService: RateApiService): RateRepo {
        return RateRepoImpl(apiService)
    }
}