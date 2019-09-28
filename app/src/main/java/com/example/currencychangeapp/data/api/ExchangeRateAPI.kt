package com.example.currencychangeapp.data.api

import com.example.currencychangeapp.data.entity.ExchangeRateEntityResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeRateAPI {

    @GET("latest")
    fun getExchangeRate(@Query("base") base: String): Single<ExchangeRateEntityResponse>
}