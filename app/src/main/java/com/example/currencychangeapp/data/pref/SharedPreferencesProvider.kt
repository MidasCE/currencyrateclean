package com.example.currencychangeapp.data.pref

import android.content.SharedPreferences

interface SharedPreferencesProvider {
    fun provide(name: String): SharedPreferences
}
