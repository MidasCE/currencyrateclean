package com.example.currencychangeapp.presentation.shared.mapper

import android.content.Context

class CurrencyFlagResourceMapperImpl(private val context: Context) :
    CurrencyFlagResourceMapper {

    override fun getCurrencyFlagResId(currencyCode: String): Int
            = context.resources.getIdentifier(
            "ic_" + currencyCode + "_flag", "drawable", context.packageName)

}
