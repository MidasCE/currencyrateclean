package com.example.currencychangeapp.data.repository

import com.example.currencychangeapp.data.api.ExchangeRateAPI
import com.example.currencychangeapp.data.entity.ExchangeRateEntityResponse
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ExchangeRateRepositoryImplTest {

    @Mock
    lateinit var api: ExchangeRateAPI

    private lateinit var exchangeRateRepositoryImpl : ExchangeRateRepositoryImpl

    @Before
    fun setUp() {
        exchangeRateRepositoryImpl = ExchangeRateRepositoryImpl(api)
    }

    @Test
    fun `Test getExchangeRate`() {
        val response = ExchangeRateEntityResponse("base", "date", emptyMap())
        val testObserver = TestObserver<ExchangeRateEntityResponse>()
        whenever(api.getExchangeRate("base")).thenReturn(
            Single.just(response))

        exchangeRateRepositoryImpl.getExchangeRate("base").subscribe(testObserver)

        testObserver.awaitTerminalEvent()
        testObserver.assertValue(response)
        verify(api).getExchangeRate("base")
    }

}
