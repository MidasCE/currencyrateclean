package com.example.currencychangeapp.data.entity

data class ExchangeRateEntityResponse(val base: String, val date: String, val rates: Map<String, Float>)
