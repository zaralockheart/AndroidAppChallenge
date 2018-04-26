package com.example.ucoppp.androidappchallenge

import com.example.ucoppp.androidappchallenge.util.isValidEmail
import com.example.ucoppp.androidappchallenge.util.validateMobileNumber
import com.example.ucoppp.androidappchallenge.util.isValidPassword
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun validatePassword() {
        assertEquals(true, "lol!asd123".isValidPassword())
    }

    @Test
    fun validateEmail() {
        assertEquals(true, "lol@asd.com".isValidEmail())
    }

    @Test
    fun validateMobileNumber() {
        assertEquals(true, "+601111111".validateMobileNumber(min = 8, max = 9))
    }
}
