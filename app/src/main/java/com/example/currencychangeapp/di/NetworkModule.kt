package com.example.currencychangeapp.di

import com.example.currencychangeapp.data.api.ExchangeRateAPI
import com.example.currencychangeapp.data.repository.ExchangeRateRepository
import com.example.currencychangeapp.data.repository.ExchangeRateRepositoryImpl
import com.example.currencychangeapp.data.serializer.DateDeserializer
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideExchangeRateAPI(retrofit: Retrofit): ExchangeRateAPI =
        retrofit.create(ExchangeRateAPI::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://revolut.duckdns.org/")
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .registerTypeAdapter(Date::class.java, DateDeserializer())
        .create()

    @Provides
    @Singleton
    fun provideExchangeRateRepository(api: ExchangeRateAPI): ExchangeRateRepository
            = ExchangeRateRepositoryImpl(api)

}
