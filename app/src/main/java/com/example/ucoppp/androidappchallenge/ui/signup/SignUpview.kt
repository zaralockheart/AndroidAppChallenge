package com.example.ucoppp.androidappchallenge.ui.signup

import com.example.ucoppp.androidappchallenge.ui.signin.EmailPasswordValidator

interface SignUpview : EmailPasswordValidator {

    // I think I need to clear all the errors first when user re-enter data

    fun onClearError()

    // Email, password, gender and mobile number are compulsory.
    // On empty compulsory fields, signup button click should show “Please complete
    // the form” message.

    fun onEmptyCompulsoryField(error: String? = "Please complete the form")

    // On wrong mobile number signup button click should show error. “Mobile number
    // is not valid ( Only check for Malaysian)

    fun onInvalidMobileNumber(error: String = "Mobile number is not valid")

    // On no gender selected, signup button click should show an error.

    fun onNoGenderSelected()

    // If nothing happens, lets sign the user up!

    fun onSignUpUser()

}