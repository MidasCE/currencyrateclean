package com.example.currencychangeapp.domain.mapper

import com.example.currencychangeapp.data.entity.ExchangeRateEntityResponse
import com.example.currencychangeapp.domain.model.RateDetail

interface ExchangeRateEntityMapper {

    fun map(exchangeRateEntityResponse: ExchangeRateEntityResponse): RateDetail
}
