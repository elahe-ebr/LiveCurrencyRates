package com.elahe.livecurrencyrates.di

import com.elahe.livecurrencyrates.data.repository.RateRepoImpl
import com.elahe.livecurrencyrates.data.remote.RateApiService
import com.elahe.livecurrencyrates.data.repository.RateRepo
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