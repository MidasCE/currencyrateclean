package com.example.currencychangeapp.data.pref

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesProviderImpl(val context: Context) : SharedPreferencesProvider {

    override fun provide(name: String): SharedPreferences {
        return context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }
}
