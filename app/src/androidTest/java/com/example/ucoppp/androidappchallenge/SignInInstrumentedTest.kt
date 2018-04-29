package com.example.ucoppp.androidappchallenge

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.ucoppp.androidappchallenge.common.checkWithToast
import com.example.ucoppp.androidappchallenge.ui.signin.SignInActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class SignInInstrumentedTest {

    @Rule
    @JvmField
    var mActivityRule: ActivityTestRule<SignInActivity> = ActivityTestRule<SignInActivity>(SignInActivity::class.java)

    private val activity by lazy {
        mActivityRule.activity
    }

    @Test
    fun testSignIn() {
        onView(withId(R.id.editTextEmail))
                .perform(typeText("someemail@mailinator.com"), closeSoftKeyboard())

        onView(withId(R.id.editTextPassword))
                .perform(typeText("someemail"), closeSoftKeyboard())

        onView(withText(R.string.text_signin)).perform(click())
    }

    @Test
    fun testClickSignUp() {
        onView(withText(R.string.text_signup)).perform(click())
    }

    @Test
    fun testEmptySomewhere() {
        onView(withId(R.id.editTextEmail))
                .perform(typeText("someemail@mailinator.com"), closeSoftKeyboard())

        onView(withText(R.string.text_signin)).perform(click())

        onView(ViewMatchers.withText(R.string.text_data_empty))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.editTextEmail))
                .perform(typeText(""), closeSoftKeyboard())

        onView(withId(R.id.editTextPassword))
                .perform(typeText("somepass"), closeSoftKeyboard())

        onView(ViewMatchers.withText(R.string.text_data_empty))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testFailSignIn() {
        onView(withId(R.id.editTextEmail))
                .perform(typeText("emailnotexist@gmail.com"), closeSoftKeyboard())

        onView(withId(R.id.editTextPassword))
                .perform(typeText("1zzzzzzz@"), closeSoftKeyboard())

        onView(withText(R.string.text_signin)).perform(click())

        checkWithToast(activity, R.string.text_fail_signin)
    }
}
