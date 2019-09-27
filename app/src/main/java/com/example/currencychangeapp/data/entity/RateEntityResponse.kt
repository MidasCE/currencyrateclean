package com.example.currencychangeapp.data.entity

data class RateEntityResponse(val base: String, val date: String, val rates: Map<String, Float>)
