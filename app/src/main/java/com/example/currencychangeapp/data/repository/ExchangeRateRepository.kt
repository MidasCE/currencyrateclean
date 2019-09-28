package com.example.currencychangeapp.data.repository

import com.example.currencychangeapp.data.entity.ExchangeRateEntityResponse
import io.reactivex.Single

interface ExchangeRateRepository {

    fun getExchangeRate(base: String) : Single<ExchangeRateEntityResponse>
}
