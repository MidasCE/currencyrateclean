package com.example.currencychangeapp.data.pref

import android.content.Context
import android.content.SharedPreferences
import com.example.currencychangeapp.data.entity.ExchangeRateEntityResponse
import com.google.gson.Gson
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.whenever
import org.amshove.kluent.`should equal`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class ExchangeRatePreferencesImplTest {

    companion object {
        internal const val PREFERENCES_NAME = "exchageRatePreferences"
    }

    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var sharedPreferencesProvider: SharedPreferencesProvider

    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var exchangeRatePreferencesImpl: ExchangeRatePreferencesImpl

    @Before
    fun setUp() {
        sharedPreferences = RuntimeEnvironment.application.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        whenever(sharedPreferencesProvider.provide(PREFERENCES_NAME)).thenReturn(sharedPreferences)

        exchangeRatePreferencesImpl = ExchangeRatePreferencesImpl(Gson(), sharedPreferencesProvider)
    }

    @Test
    fun `Test saveExchangeRate`() {
        val response = ExchangeRateEntityResponse("base", "date", emptyMap())

        exchangeRatePreferencesImpl.saveExchangeRate("base", response)

        exchangeRatePreferencesImpl.getExchangeRate("base").`should equal`(response)
        exchangeRatePreferencesImpl.getExchangeRate("currency").`should equal`(null)
    }
}
