package com.example.currencychangeapp.data.repository

import com.example.currencychangeapp.data.api.ExchangeRateAPI
import com.example.currencychangeapp.data.entity.ExchangeRateEntityResponse
import com.example.currencychangeapp.data.pref.ExchangeRatePreferences
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

    @Mock
    lateinit var exchangeRatePreferences: ExchangeRatePreferences

    private lateinit var exchangeRateRepositoryImpl : ExchangeRateRepositoryImpl

    @Before
    fun setUp() {
        exchangeRateRepositoryImpl = ExchangeRateRepositoryImpl(api, exchangeRatePreferences)
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
        verify(exchangeRatePreferences).saveExchangeRate("base", response)
    }

    @Test
    fun `Test getExchangeRate with error return cache`() {
        val response = ExchangeRateEntityResponse("base", "date", emptyMap())
        val testObserver = TestObserver<ExchangeRateEntityResponse>()
        whenever(api.getExchangeRate("base")).thenReturn(
            Single.error(Throwable()))
        whenever(exchangeRatePreferences.getExchangeRate("base")).thenReturn(
            response)

        exchangeRateRepositoryImpl.getExchangeRate("base").subscribe(testObserver)

        testObserver.awaitTerminalEvent()
        testObserver.assertValue(response)
        verify(api).getExchangeRate("base")
        verify(exchangeRatePreferences).getExchangeRate("base")
    }


}
