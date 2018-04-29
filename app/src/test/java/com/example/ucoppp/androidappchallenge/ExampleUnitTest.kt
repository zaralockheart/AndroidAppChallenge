package com.example.ucoppp.androidappchallenge

import android.support.v7.app.AppCompatActivity
import com.example.ucoppp.androidappchallenge.database.user.User
import com.example.ucoppp.androidappchallenge.ui.signup.SignUpPresenter
import com.example.ucoppp.androidappchallenge.ui.signup.SignUpview
import com.example.ucoppp.androidappchallenge.util.isValidEmail
import com.example.ucoppp.androidappchallenge.util.isValidMobileNumber
import com.example.ucoppp.androidappchallenge.util.isValidPassword
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.item_edittext_email.*
import kotlinx.android.synthetic.main.item_edittext_mobile.*
import kotlinx.android.synthetic.main.item_edittext_password.*
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
        assertEquals(true, "1234567890".isValidMobileNumber())
    }

    @Test
    fun validateSignUp() {

        val user = User(
                email = "someemail2@gmail.com",
                password = "someem@il",
                firstName = "Firstname",
                lastName = "Lastname",
                mobileNumber = "01119260682",
                gender = "Male"
        )

        assertEquals(
                true,
                SignUpPresenter(null, null).onSignUpUser(user))

//        assertEquals(
//                false,
//                SignUpPresenter(null, null).checkEmail(user))
    }
}
