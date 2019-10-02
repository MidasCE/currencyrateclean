package com.example.currencychangeapp.data.pref

import android.content.SharedPreferences
import com.example.currencychangeapp.data.entity.ExchangeRateEntityResponse
import com.google.gson.Gson

class ExchangeRatePreferencesImpl(private val gson: Gson,
                                  sharedPreferencesProvider: SharedPreferencesProvider)
    : ExchangeRatePreferences {

    companion object {
        const val EXCHANGE_RATE_PREF = "cachedRatePref"

        internal const val PREFERENCES_NAME = "exchageRatePreferences"
    }

    private val preferences: SharedPreferences = sharedPreferencesProvider.provide(PREFERENCES_NAME)

    override fun saveExchangeRate(base: String, response: ExchangeRateEntityResponse) {
        val jsonData = gson.toJson(response)
        preferences.edit().putString("$EXCHANGE_RATE_PREF.$base", jsonData).apply()
    }

    override fun getExchangeRate(base: String): ExchangeRateEntityResponse? {
        val cachedData = preferences.getString("$EXCHANGE_RATE_PREF.$base", null)
        return gson.fromJson(cachedData, ExchangeRateEntityResponse::class.java)
    }

}
