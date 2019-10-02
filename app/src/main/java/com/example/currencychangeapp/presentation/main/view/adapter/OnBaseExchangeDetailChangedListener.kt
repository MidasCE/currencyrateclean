package com.example.currencychangeapp.presentation.main.view.adapter

interface OnBaseExchangeDetailChangedListener {

    fun onAmountChanged(amount: Float)

    fun onBaseChanged(base: String, amount: Float)
}
