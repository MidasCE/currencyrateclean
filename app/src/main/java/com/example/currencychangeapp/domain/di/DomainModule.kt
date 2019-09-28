package com.example.currencychangeapp.domain.di

import com.example.currencychangeapp.data.repository.ExchangeRateRepository
import com.example.currencychangeapp.domain.interactor.GetExchangeRateInteractor
import com.example.currencychangeapp.domain.interactor.GetExchangeRateInteractorImpl
import com.example.currencychangeapp.domain.mapper.ExchangeRateEntityMapper
import com.example.currencychangeapp.domain.mapper.ExchangeRateEntityMapperImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    @Singleton
    fun provideExchangeRateEntityMapper(): ExchangeRateEntityMapper = ExchangeRateEntityMapperImpl()

    @Provides
    @Singleton
    fun provideGetExchangeRateInteractor(exchangeRateRepository: ExchangeRateRepository,
                                         exchangeRateEntityMapper: ExchangeRateEntityMapper)
            : GetExchangeRateInteractor = GetExchangeRateInteractorImpl(exchangeRateRepository,
        exchangeRateEntityMapper)

}
