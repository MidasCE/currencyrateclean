package com.example.currencychangeapp.data.repository

import com.example.currencychangeapp.data.api.ExchangeRateAPI
import com.example.currencychangeapp.data.entity.ExchangeRateEntityResponse
import com.example.currencychangeapp.data.pref.ExchangeRatePreferences
import com.example.currencychangeapp.data.pref.SharedPreferencesProvider
import io.reactivex.Single

class ExchangeRateRepositoryImpl(private val exchangeRateAPI: ExchangeRateAPI,
                                 private val exchangeRatePreferences: ExchangeRatePreferences
) : ExchangeRateRepository {

    override fun getExchangeRate(base: String): Single<ExchangeRateEntityResponse> {
        return exchangeRateAPI.getExchangeRate(base).onErrorResumeNext {
            Single.just(exchangeRatePreferences.getExchangeRate(base))
        }.doOnSuccess {
            exchangeRatePreferences.saveExchangeRate(base, it)
        }
    }
}
