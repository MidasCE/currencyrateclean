package com.example.currencychangeapp.data.repository

import com.example.currencychangeapp.data.api.ExchangeRateAPI
import com.example.currencychangeapp.data.entity.ExchangeRateEntityResponse
import io.reactivex.Single

class ExchangeRateRepositoryImpl(private val exchangeRateAPI: ExchangeRateAPI) : ExchangeRateRepository {

    override fun getExchangeRate(base: String): Single<ExchangeRateEntityResponse> = exchangeRateAPI.getExchangeRate(base)
}
