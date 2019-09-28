package com.example.currencychangeapp.domain.interactor

import com.example.currencychangeapp.data.repository.ExchangeRateRepository
import com.example.currencychangeapp.domain.mapper.ExchangeRateEntityMapper
import com.example.currencychangeapp.domain.model.RateDetail
import io.reactivex.Single

class GetExchangeRateInteractorImpl(
    private val exchangeRateRepository: ExchangeRateRepository,
    private val exchangeRateEntityMapper: ExchangeRateEntityMapper
) : GetExchangeRateInteractor {

    override fun getExchangeRateDetail(base: String): Single<RateDetail> =
        exchangeRateRepository.getExchangeRate(base).map {
            exchangeRateEntityMapper.map(it)
        }

}
