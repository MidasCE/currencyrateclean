package com.example.currencychangeapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.currencychangeapp.Extension.Companion.recyclerViewNotEmptyMatcher
import com.example.currencychangeapp.presentation.main.view.MainActivity
import com.example.currencychangeapp.presentation.main.view.adapter.ExchangeRateItemAdapter

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class UIFlowTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun checkDataLoadProperlyWhenLaunchApp() {
        Extension.waitUntilViewMatched(
            withId(R.id.recyclerCurrencies),
            matches(isDisplayed()),
            3000
        )

        onView(withId(R.id.recyclerCurrencies)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.recyclerCurrencies)).check(matches(recyclerViewNotEmptyMatcher()))
    }

    @Test
    fun checkWhenTapCurrency_BaseCurrencyChange() {
        //TODO This test is rely on API. We need to have mock server to test this test case properly.
        Extension.waitUntilViewMatched(
            withId(R.id.recyclerCurrencies),
            matches(isDisplayed()),
            3000
        )

        onView(withId(R.id.recyclerCurrencies))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ExchangeRateItemAdapter.ViewHolder>(
                    1,
                    Extension.clickChildEditTextViewWithId(R.id.currencyAmountEditText)
                )
            )

        onView(withRecyclerView(R.id.recyclerCurrencies)
            .atPositionOnView(0, R.id.currencyCodeTextView)).check(matches(withText("AUD")))
    }
}
