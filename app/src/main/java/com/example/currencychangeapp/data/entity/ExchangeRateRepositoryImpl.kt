package com.example.currencychangeapp.data.entity

import com.example.currencychangeapp.data.repository.ExchangeRateRepository
import io.reactivex.Single

class ExchangeRateRepositoryImpl : ExchangeRateRepository {

    override fun getExchangeRate(base: String): Single<RateEntityResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
