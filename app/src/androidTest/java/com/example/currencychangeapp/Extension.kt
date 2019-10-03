package com.example.currencychangeapp

import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers

class Extension {
    companion object {
        fun waitUntilViewMatched(
            viewMatcher: Matcher<View>,
            assertion: ViewAssertion,
            durationInMillis: Long
        ) {
            val startTime = System.currentTimeMillis()
            while (true) {
                var vi: ViewInteraction? = null
                try {
                    vi = onView(viewMatcher)
                } catch (e: Throwable) {
                    //Do nothing
                }

                if (vi != null) {
                    try {
                        vi.check(assertion)
                        break
                    } catch (e: Throwable) {
                        //Do nothing
                    }

                }
                val diff = System.currentTimeMillis() - startTime
                if (diff > durationInMillis) {
                    MatcherAssert.assertThat<Any>(
                        "waitUntilViewMatched() Timeout reached (Aborted)",
                        true, Matchers.not(Matchers.anything())
                    )
                }
                Thread.sleep(100)
            }
        }

        fun recyclerViewNotEmptyMatcher(): Matcher<View> {
            return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
                override fun describeTo(description: Description) {
                    description.appendText("with recycler view not empty")
                }

                override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                    return recyclerView.adapter!!.itemCount > 0
                }
            }
        }


        fun clickChildEditTextViewWithId(id: Int) : ViewAction {
            return object : ViewAction {
                override fun getConstraints(): Matcher<View>? {
                    return null
                }

                override fun getDescription(): String {
                    return "Click on a child view with specified id : $id."
                }

                override fun perform(uiController: UiController, view: View) {
                    val v = view.findViewById<EditText>(id)
                    v.requestFocus()
                }
            }
        }


    }
}
