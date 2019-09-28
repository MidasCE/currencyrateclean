package com.example.currencychangeapp.presentation.main.mapper

import com.example.currencychangeapp.domain.model.ExchangeRate
import com.example.currencychangeapp.presentation.main.model.ExchangeRateItemViewModel

class ExchangeRateItemViewModelMapperImpl : ExchangeRateItemViewModelMapper {

    override fun map(exchangeRate: ExchangeRate): ExchangeRateItemViewModel =
        ExchangeRateItemViewModel(exchangeRate.currency, "", exchangeRate.rate)

}