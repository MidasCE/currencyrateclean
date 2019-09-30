package com.example.currencychangeapp.presentation.shared.mapper

import android.content.Context

class CurrencyStringMapperImpl(private val context: Context) :
    CurrencyStringMapper {

    override fun getCurrencyName(currencyCode: String): String
         = context.getString(context.resources.getIdentifier(currencyCode + "_name", "string",
            context.packageName))

}
