package com.example.currencychangeapp.presentation.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.currencychangeapp.R
import com.example.currencychangeapp.presentation.main.model.ExchangeRateItemViewModel
import com.example.currencychangeapp.presentation.main.presenter.MainActivityPresenter
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : MainView, AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var presenter: MainActivityPresenter

    @Inject
    lateinit var adapter: ExchangeRateItemAdapter

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    private lateinit var exhangeRateRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        presenter.loadExchangeRate("EU")
    }

    private fun initView() {
        exhangeRateRecyclerView = findViewById(R.id.recyclerCurrencies)
        exhangeRateRecyclerView.layoutManager = LinearLayoutManager(this)
        exhangeRateRecyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onActivityDestroy()
    }

    override fun updateExchangeRate(list: List<ExchangeRateItemViewModel>) {
        val inFromBottom = TranslateAnimation(
            Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
            Animation.RELATIVE_TO_PARENT, +1.0f, Animation.RELATIVE_TO_PARENT, 0.0f
        )
        inFromBottom.duration = 1000
        exhangeRateRecyclerView.startAnimation(inFromBottom)

        adapter.exchangeRateItemList = list
        adapter.notifyDataSetChanged()
    }

    override fun showLoading() {

    }

    override fun showError() {

    }
}
