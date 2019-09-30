package com.example.currencychangeapp.presentation.main.di

import com.example.currencychangeapp.domain.interactor.GetExchangeRateInteractor
import com.example.currencychangeapp.presentation.core.SchedulerFactory
import com.example.currencychangeapp.presentation.main.mapper.*
import com.example.currencychangeapp.presentation.main.presenter.MainActivityPresenter
import com.example.currencychangeapp.presentation.main.presenter.MainActivityPresenterImpl
import com.example.currencychangeapp.presentation.main.view.adapter.ExchangeRateItemAdapter
import com.example.currencychangeapp.presentation.main.view.MainActivity
import com.example.currencychangeapp.presentation.main.view.MainView
import com.example.currencychangeapp.presentation.shared.mapper.CurrencyFlagResourceMapper
import com.example.currencychangeapp.presentation.shared.mapper.CurrencyStringMapper
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun provideMainView(mainActivity: MainActivity): MainView = mainActivity

    @Provides
    fun provideMainActivityPresenter(
        viewModelMapper: ExchangeRateItemViewModelMapper,
        getExchangeRateInteractor: GetExchangeRateInteractor,
        schedulerFactory: SchedulerFactory,
        mainView: MainView
    ): MainActivityPresenter =
        MainActivityPresenterImpl(
            viewModelMapper,
            getExchangeRateInteractor,
            schedulerFactory,
            mainView
        )

    @Provides
    fun provideExchangeRateItemViewModelMapper(): ExchangeRateItemViewModelMapper =
        ExchangeRateItemViewModelMapperImpl()

    @Provides
    fun provideExchangeRateItemAdapter(
        mainActivity: MainActivity,
        currencyStringMapper: CurrencyStringMapper,
        currencyFlagResourceMapper: CurrencyFlagResourceMapper
    ): ExchangeRateItemAdapter =
        ExchangeRateItemAdapter(mainActivity, currencyStringMapper, currencyFlagResourceMapper)

}
