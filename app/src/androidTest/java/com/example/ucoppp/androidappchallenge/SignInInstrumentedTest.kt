package com.example.ucoppp.androidappchallenge

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.ucoppp.androidappchallenge.common.checkWithToast
import com.example.ucoppp.androidappchallenge.ui.signin.SignInActivity
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matchers.`is`
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
    fun testFailSignIn() {
        onView(withId(R.id.editTextEmail))
                .perform(typeText("emailnotexist@gmail.com"), closeSoftKeyboard())

        onView(withId(R.id.editTextPassword))
                .perform(typeText("1zzzzzzz@"), closeSoftKeyboard())

        onView(withText(R.string.text_signin)).perform(click())

        checkWithToast(activity, R.string.text_fail_signin)
    }
}
