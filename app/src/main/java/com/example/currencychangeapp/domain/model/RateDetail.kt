package com.example.currencychangeapp.domain.model

data class RateDetail(val base: String,
                      val date: String,
                      val exchangeRateList: List<ExchangeRate>)