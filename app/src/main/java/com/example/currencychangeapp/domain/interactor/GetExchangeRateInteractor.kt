package com.example.currencychangeapp.domain.interactor

import com.example.currencychangeapp.domain.model.RateDetail
import io.reactivex.Single

interface GetExchangeRateInteractor {

    fun getExchangeRateDetail(base: String): Single<RateDetail>
}
