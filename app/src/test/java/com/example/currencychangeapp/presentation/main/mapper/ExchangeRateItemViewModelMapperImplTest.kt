package com.example.currencychangeapp.presentation.main.mapper

import com.example.currencychangeapp.domain.model.ExchangeRate
import org.amshove.kluent.`should equal`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ExchangeRateItemViewModelMapperImplTest {

    lateinit var exchangeRateItemViewModelMapperImpl: ExchangeRateItemViewModelMapperImpl

    @Before
    fun setUp() {
        exchangeRateItemViewModelMapperImpl = ExchangeRateItemViewModelMapperImpl()
    }

    @Test
    fun `Test map`() {
        val amount = 3.1f
        val exchangeRate = ExchangeRate("currency", 1f)
        val result = exchangeRateItemViewModelMapperImpl.map(amount, exchangeRate)

        result.currencyCode.`should equal`(exchangeRate.currency)
        result.exchangeAmount.`should equal`(amount * exchangeRate.rate)
    }
}

