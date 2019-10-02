package com.example.currencychangeapp.presentation.main.presenter

import com.example.currencychangeapp.domain.interactor.GetExchangeRateInteractor
import com.example.currencychangeapp.domain.model.ExchangeRate
import com.example.currencychangeapp.domain.model.RateDetail
import com.example.currencychangeapp.presentation.core.SchedulerFactory
import com.example.currencychangeapp.presentation.main.mapper.ExchangeRateItemViewModelMapper
import com.example.currencychangeapp.presentation.main.model.ExchangeRateItemViewModel
import com.example.currencychangeapp.presentation.main.view.MainView
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.TimeUnit

@RunWith(MockitoJUnitRunner::class)
class MainActivityPresenterImplTest {

    @Mock
    lateinit var viewModelMapper: ExchangeRateItemViewModelMapper

    @Mock
    lateinit var getExchangeRateInteractor: GetExchangeRateInteractor

    @Mock
    lateinit var schedulerFactory: SchedulerFactory

    @Mock
    lateinit var mainView: MainView

    private lateinit var ioScheduler: TestScheduler

    private lateinit var mainScheduler: TestScheduler

    private lateinit var presenter : MainActivityPresenterImpl

    @Before
    fun setUp() {
        ioScheduler = TestScheduler()
        mainScheduler = TestScheduler()

        whenever(schedulerFactory.io()).thenReturn(ioScheduler)
        whenever(schedulerFactory.main()).thenReturn(mainScheduler)

        presenter = MainActivityPresenterImpl(viewModelMapper,
            getExchangeRateInteractor,
            schedulerFactory,
            mainView)
    }

    @Test
    fun `Test loadExchangeRate`() {
        var amount = 2f
        val rateDetail = RateDetail("base", "date", emptyList())
        val resultViewModel = ExchangeRateItemViewModel("code", "name", 4f)
        whenever(getExchangeRateInteractor.getExchangeRateDetail("base")).thenReturn(Single.just(
            rateDetail
        ))
        whenever(viewModelMapper.map(any(), any<ExchangeRate>())).thenReturn(
            resultViewModel
        )

        presenter.loadExchangeRate("base", amount)
        verify(mainView).showLoading()

        ioScheduler.triggerActions()
        mainScheduler.triggerActions()
        ioScheduler.advanceTimeBy(1, TimeUnit.SECONDS)
        mainScheduler.advanceTimeBy(1, TimeUnit.SECONDS)

        verify(mainView).hideLoading()
        verify(viewModelMapper).map(eq(amount), any())
        verify(mainView).updateExchangeRate(any<List<ExchangeRateItemViewModel>>())

        amount = 3f
        presenter.updateBaseAmount(amount)
        ioScheduler.advanceTimeBy(1, TimeUnit.SECONDS)
        mainScheduler.advanceTimeBy(1, TimeUnit.SECONDS)
        verify(viewModelMapper).map(eq(amount), any())
    }

    @Test
    fun `Test loadExchangeRate with error`() {
        var amount = 2f
        whenever(getExchangeRateInteractor.getExchangeRateDetail("base")).thenReturn(Single.error(
            Throwable()
        ))

        presenter.loadExchangeRate("base", amount)
        verify(mainView).showLoading()

        ioScheduler.triggerActions()
        mainScheduler.triggerActions()
        ioScheduler.advanceTimeBy(1, TimeUnit.SECONDS)
        mainScheduler.advanceTimeBy(1, TimeUnit.SECONDS)

        verify(mainView).hideLoading()
        verify(mainView).showError()
        verify(viewModelMapper, never()).map(eq(amount), any())
        verify(mainView, never()).updateExchangeRate(any<List<ExchangeRateItemViewModel>>())
    }

}
