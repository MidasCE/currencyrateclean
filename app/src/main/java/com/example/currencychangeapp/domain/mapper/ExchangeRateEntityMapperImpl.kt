package com.example.currencychangeapp.domain.mapper

import com.example.currencychangeapp.data.entity.ExchangeRateEntityResponse
import com.example.currencychangeapp.domain.model.ExchangeRate
import com.example.currencychangeapp.domain.model.RateDetail

class ExchangeRateEntityMapperImpl : ExchangeRateEntityMapper {

    override fun map(exchangeRateEntityResponse: ExchangeRateEntityResponse): RateDetail =
        exchangeRateEntityResponse.let {
            RateDetail(
                it.base,
                it.date,
                it.rates.map { entry ->
                    ExchangeRate(entry.key, entry.value)
                }
            )
        }

}
