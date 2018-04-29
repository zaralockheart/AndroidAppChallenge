package com.example.ucoppp.androidappchallenge.common

import android.app.Activity
import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.RootMatchers
import android.support.test.espresso.matcher.ViewMatchers
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers

fun checkWithToast(activity: Activity, expectedString: Int) {
    Espresso.onView(ViewMatchers.withText(expectedString))
            .inRoot(RootMatchers.withDecorView(CoreMatchers.not(Matchers.`is`(activity.window.decorView))))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
}

fun checkWithToast(activity: Activity, expectedString: String) {
    Espresso.onView(ViewMatchers.withText(expectedString))
            .inRoot(RootMatchers.withDecorView(CoreMatchers.not(Matchers.`is`(activity.window.decorView))))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
}