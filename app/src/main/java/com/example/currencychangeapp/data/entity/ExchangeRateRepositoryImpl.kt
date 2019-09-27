package com.example.currencychangeapp.data.entity

import com.example.currencychangeapp.data.api.ExchangeRateAPI
import com.example.currencychangeapp.data.repository.ExchangeRateRepository
import io.reactivex.Single

class ExchangeRateRepositoryImpl(private val exchangeRateAPI: ExchangeRateAPI) : ExchangeRateRepository {

    override fun getExchangeRate(base: String): Single<RateEntityResponse> = exchangeRateAPI.getExchangeRate(base)
}
