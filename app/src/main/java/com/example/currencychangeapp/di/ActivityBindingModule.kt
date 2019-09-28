package com.example.currencychangeapp.di

import com.example.currencychangeapp.presentation.main.di.MainActivityModule
import com.example.currencychangeapp.presentation.main.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivity(): MainActivity
}
