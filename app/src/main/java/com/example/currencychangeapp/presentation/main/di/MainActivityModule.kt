package com.example.currencychangeapp.presentation.main.di

import com.example.currencychangeapp.domain.interactor.GetExchangeRateInteractor
import com.example.currencychangeapp.presentation.core.SchedulerFactory
import com.example.currencychangeapp.presentation.main.mapper.ExchangeRateItemViewModelMapper
import com.example.currencychangeapp.presentation.main.mapper.ExchangeRateItemViewModelMapperImpl
import com.example.currencychangeapp.presentation.main.presenter.MainActivityPresenter
import com.example.currencychangeapp.presentation.main.presenter.MainActivityPresenterImpl
import com.example.currencychangeapp.presentation.main.view.ExchangeRateItemAdapter
import com.example.currencychangeapp.presentation.main.view.MainActivity
import com.example.currencychangeapp.presentation.main.view.MainView
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
class MainActivityModule {

    @Provides
    fun provideMainView(mainActivity: MainActivity) : MainView = mainActivity

    @Provides
    fun provideMainActivityPresenter(viewModelMapper: ExchangeRateItemViewModelMapper,
                                     getExchangeRateInteractor: GetExchangeRateInteractor,
                                     schedulerFactory: SchedulerFactory,
                                     mainView: MainView) : MainActivityPresenter =
        MainActivityPresenterImpl(viewModelMapper,
            getExchangeRateInteractor,
            schedulerFactory,
            mainView)

    @Provides
    fun provideExchangeRateItemViewModelMapper(): ExchangeRateItemViewModelMapper =
        ExchangeRateItemViewModelMapperImpl()

    @Provides
    fun provideExchangeRateItemAdapter(): ExchangeRateItemAdapter =
        ExchangeRateItemAdapter()

}
