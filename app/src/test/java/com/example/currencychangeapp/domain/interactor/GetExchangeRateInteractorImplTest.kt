package com.example.currencychangeapp.domain.interactor

import com.example.currencychangeapp.data.entity.ExchangeRateEntityResponse
import com.example.currencychangeapp.data.repository.ExchangeRateRepository
import com.example.currencychangeapp.domain.mapper.ExchangeRateEntityMapper
import com.example.currencychangeapp.domain.model.RateDetail
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetExchangeRateInteractorImplTest {

    @Mock
    lateinit var exchangeRateRepository: ExchangeRateRepository

    @Mock
    lateinit var exchangeRateEntityMapper: ExchangeRateEntityMapper

    lateinit var getExchangeRateInteractorImpl: GetExchangeRateInteractorImpl

    @Before
    fun setUp() {
        getExchangeRateInteractorImpl =
            GetExchangeRateInteractorImpl(exchangeRateRepository, exchangeRateEntityMapper)
    }

    @Test
    fun `Test getExchangeRateDetail`() {
        val response = ExchangeRateEntityResponse("base", "date", emptyMap())
        val rateDetail = RateDetail("base", "date", emptyList())
        val testObserver = TestObserver<RateDetail>()
        whenever(exchangeRateRepository.getExchangeRate("base")).thenReturn(
            Single.just(response))
        whenever(exchangeRateEntityMapper.map(any())).thenReturn(
            rateDetail)

        getExchangeRateInteractorImpl.getExchangeRateDetail("base").subscribe(testObserver)

        testObserver.awaitTerminalEvent()
        testObserver.assertValue(rateDetail)
        verify(exchangeRateRepository).getExchangeRate("base")
        verify(exchangeRateEntityMapper).map(response)
    }

}
