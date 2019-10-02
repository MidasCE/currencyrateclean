package com.example.currencychangeapp.presentation.main.presenter

import com.example.currencychangeapp.domain.interactor.GetExchangeRateInteractor
import com.example.currencychangeapp.domain.model.ExchangeRate
import com.example.currencychangeapp.presentation.core.SchedulerFactory
import com.example.currencychangeapp.presentation.main.view.MainView
import io.reactivex.disposables.CompositeDisposable
import com.example.currencychangeapp.presentation.main.mapper.ExchangeRateItemViewModelMapper
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.SerialDisposable
import java.util.concurrent.TimeUnit

class MainActivityPresenterImpl(
    private val viewModelMapper: ExchangeRateItemViewModelMapper,
    private val getExchangeRateInteractor: GetExchangeRateInteractor,
    private val schedulerFactory: SchedulerFactory,
    private val mainView: MainView
) : MainActivityPresenter {

    private var serialDisposable = SerialDisposable()

    private var currentBase = ""
    private var currentAmount = 0F

    override fun loadExchangeRate(base: String, amount: Float) {
        mainView.showLoading()
        currentBase = base
        currentAmount = amount
        serialDisposable.set(fetchExchangeRate(currentBase))
    }

    override fun updateBaseAmount(amount: Float) {
        currentAmount = amount
    }

    private fun fetchExchangeRate(base: String): Disposable {
        return Observable.interval(1, TimeUnit.SECONDS).flatMap {
            getExchangeRateInteractor.getExchangeRateDetail(base).toObservable()
        }
            .subscribeOn(schedulerFactory.io())
            .observeOn(schedulerFactory.main())
            .subscribe({ rateDetail ->
                val exchangeRateList = rateDetail.exchangeRateList.toMutableList()
                exchangeRateList.add(0, ExchangeRate(base, 1F))
                val list = exchangeRateList.map {
                    viewModelMapper.map(currentAmount, it)
                }
                mainView.hideLoading()
                mainView.updateExchangeRate(list)
            }, {
                mainView.showError()
            })
    }

    override fun onActivityDestroy() {
        serialDisposable.dispose()
    }
}
