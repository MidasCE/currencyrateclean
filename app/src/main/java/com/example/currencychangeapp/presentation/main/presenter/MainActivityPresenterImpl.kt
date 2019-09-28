package com.example.currencychangeapp.presentation.main.presenter

import com.example.currencychangeapp.domain.interactor.GetExchangeRateInteractor
import com.example.currencychangeapp.presentation.core.SchedulerFactory
import com.example.currencychangeapp.presentation.main.view.MainView
import io.reactivex.disposables.CompositeDisposable
import com.example.currencychangeapp.presentation.main.mapper.ExchangeRateItemViewModelMapper

class MainActivityPresenterImpl(
    private val viewModelMapper: ExchangeRateItemViewModelMapper,
    private val getExchangeRateInteractor: GetExchangeRateInteractor,
    private val schedulerFactory: SchedulerFactory,
    private val mainView: MainView
) : MainActivityPresenter {

    private var compositeDisposable = CompositeDisposable()

    override fun loadExchangeRate(base: String) {
        mainView.showLoading()
        val disposable = getExchangeRateInteractor.getExchangeRateDetail(base)
            .subscribeOn(schedulerFactory.io())
            .observeOn(schedulerFactory.main())
            .subscribe({ rateDetail ->
                val list = rateDetail.exchangeRateList.map {
                    viewModelMapper.map(it)
                }
                mainView.updateExchangeRate(list)
            }, {
                mainView.showError()
            })

        compositeDisposable.add(disposable)
    }

    override fun onActivityDestroy() {
        compositeDisposable.dispose()
    }
}
