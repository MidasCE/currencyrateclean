package com.example.currencychangeapp.presentation.main.view

import com.example.currencychangeapp.presentation.main.model.ExchangeRateItemViewModel

interface MainView {
    fun updateExchangeRate(list: List<ExchangeRateItemViewModel>)

    fun showLoading()

    fun showError()
}