package com.example.currencychangeapp.presentation.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.currencychangeapp.R
import com.example.currencychangeapp.presentation.main.model.ExchangeRateItemViewModel
import com.example.currencychangeapp.presentation.main.presenter.MainActivityPresenter
import com.example.currencychangeapp.presentation.main.view.adapter.ExchangeRateItemAdapter
import com.example.currencychangeapp.presentation.main.view.adapter.OnBaseExchangeDetailChangedListener
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject
import androidx.recyclerview.widget.SimpleItemAnimator
import com.google.android.material.snackbar.Snackbar

class MainActivity : MainView, AppCompatActivity(), HasAndroidInjector, OnBaseExchangeDetailChangedListener {

    @Inject
    lateinit var presenter: MainActivityPresenter

    @Inject
    lateinit var adapter: ExchangeRateItemAdapter

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    private lateinit var exhangeRateRecyclerView: RecyclerView
    private lateinit var loadingView: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        presenter.loadExchangeRate("EUR", 100F)
    }

    private fun initView() {
        exhangeRateRecyclerView = findViewById(R.id.recyclerCurrencies)
        loadingView = findViewById(R.id.progressBar)
        exhangeRateRecyclerView.layoutManager = LinearLayoutManager(this)
        exhangeRateRecyclerView.adapter = adapter
        (exhangeRateRecyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onActivityDestroy()
    }

    override fun updateExchangeRate(list: List<ExchangeRateItemViewModel>) {
        exhangeRateRecyclerView.visibility = VISIBLE
        adapter.updateRateItem(list)
        adapter.notifyItemRangeChanged(1, list.size -1)
    }

    override fun onBaseChanged(base: String, amount: Float) {
        presenter.loadExchangeRate(base, amount)
    }

    override fun onAmountChanged(amount: Float) {
        presenter.updateBaseAmount(amount)
    }

    override fun showLoading() {
        loadingView.visibility = VISIBLE
    }

    override fun hideLoading() {
        loadingView.visibility = GONE
    }

    override fun showError() {
        Snackbar.make( findViewById(android.R.id.content), "There is an error. Please try again later", Snackbar.LENGTH_LONG).show()
    }
}
