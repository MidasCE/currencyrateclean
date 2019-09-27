package com.example.currencychangeapp.data.api

import com.example.currencychangeapp.data.entity.RateEntityResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeRateAPI {

    @GET("latest")
    fun getWeatherForecast(@Query("base") base: String): Single<RateEntityResponse>
}