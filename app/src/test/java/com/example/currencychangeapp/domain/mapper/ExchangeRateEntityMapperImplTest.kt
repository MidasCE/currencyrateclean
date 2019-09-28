package com.example.currencychangeapp.domain.mapper

import com.example.currencychangeapp.data.entity.ExchangeRateEntityResponse
import com.example.currencychangeapp.domain.model.ExchangeRate
import org.amshove.kluent.`should equal`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ExchangeRateEntityMapperImplTest {

    lateinit var mapper: ExchangeRateEntityMapperImpl

    @Before
    fun setUp(){
        mapper = ExchangeRateEntityMapperImpl()
    }

    @Test
    fun `Test map object`() {
        val response = ExchangeRateEntityResponse("base", "date", mapOf(Pair("key", 1f)))
        val result = mapper.map(response)

        result.base.`should equal`(response.base)
        result.date.`should equal`(response.date)
        result.exchangeRateList.`should equal`(listOf(ExchangeRate("key", 1f)))
    }
}
