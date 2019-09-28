package com.example.currencychangeapp.presentation.main.mapper

import com.example.currencychangeapp.domain.model.ExchangeRate
import com.example.currencychangeapp.presentation.main.model.ExchangeRateItemViewModel

interface ExchangeRateItemViewModelMapper {

    fun map(exchangeRate: ExchangeRate): ExchangeRateItemViewModel
}
