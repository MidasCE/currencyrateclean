package com.example.currencychangeapp.presentation.main.presenter

interface MainActivityPresenter {

    fun loadExchangeRate(base : String)

    fun onActivityDestroy()
}
