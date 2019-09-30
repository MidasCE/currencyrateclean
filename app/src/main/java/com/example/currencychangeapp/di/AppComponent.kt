package com.example.currencychangeapp.di

import android.app.Application
import com.example.currencychangeapp.App
import com.example.currencychangeapp.domain.di.DomainModule
import com.example.currencychangeapp.presentation.shared.di.PresentationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        // The android injection modules
        // enable hiding boiler plate code
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        ActivityBindingModule::class,
        PresentationModule::class,
        DomainModule::class,
        NetworkModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}
