package com.example.currencychangeapp.presentation.shared.di

import android.content.Context
import com.example.currencychangeapp.presentation.shared.mapper.CurrencyFlagResourceMapper
import com.example.currencychangeapp.presentation.shared.mapper.CurrencyFlagResourceMapperImpl
import com.example.currencychangeapp.presentation.shared.mapper.CurrencyStringMapper
import com.example.currencychangeapp.presentation.shared.mapper.CurrencyStringMapperImpl
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @Provides
    fun provideCurrencyFlagResourceMapper(context: Context): CurrencyFlagResourceMapper =
        CurrencyFlagResourceMapperImpl(context)

    @Provides
    fun provideCurrencyStringMapper(context: Context): CurrencyStringMapper =
        CurrencyStringMapperImpl(context)

}
