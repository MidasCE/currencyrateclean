package com.example.currencychangeapp.presentation.main.presenter

interface MainActivityPresenter {

    fun loadExchangeRate(base : String, amount: Float)

    fun updateBaseAmount(amount: Float)

    fun onActivityDestroy()
}
