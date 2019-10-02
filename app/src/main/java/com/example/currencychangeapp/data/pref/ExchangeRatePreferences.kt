package com.example.currencychangeapp.data.pref

import com.example.currencychangeapp.data.entity.ExchangeRateEntityResponse

interface ExchangeRatePreferences {

    fun saveExchangeRate(base: String, response: ExchangeRateEntityResponse)

    fun getExchangeRate(base: String): ExchangeRateEntityResponse?
}
