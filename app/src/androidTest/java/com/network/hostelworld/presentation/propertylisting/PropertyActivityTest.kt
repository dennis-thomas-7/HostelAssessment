package com.network.hostelworld.presentation.propertylisting

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.network.hostelworld.R
import org.junit.*
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class PropertyActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(PropertyActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.network.hostelworld", appContext.packageName)
    }


    @Test
    fun testRecyclerViewItemClick() {
        // Test RecyclerView item click
        onView(withId(R.id.recyclerView))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

    }

    @Test
    fun testButtonVisibility() {

        try {
            onView(withId(R.id.btnHostels))
                .check(matches(isDisplayed()))
            // View is in hierarchy
        } catch (e: NoMatchingViewException) {
            // View is not in hierarchy
        }
        try {
            onView(withId(R.id.btnRooms))
                .check(matches(isDisplayed()))
            // View is in hierarchy
        }catch (e: NoMatchingViewException){
            // View is not in hierarchy
        }
        try {
            onView(withId(R.id.btnHomes))
                .check(matches(isDisplayed()))
            // View is in hierarchy
        }catch (e: NoMatchingViewException){
            // View is not in hierarchy
        }


    }


    @Test
    fun testRecyclerViewVisibility() {

        try {
            onView(withId(R.id.recyclerView))
                .check(matches(isDisplayed()))
            // View is in hierarchy
        }catch (e: NoMatchingViewException){
            // View is not in hierarchy
        }

    }

}
