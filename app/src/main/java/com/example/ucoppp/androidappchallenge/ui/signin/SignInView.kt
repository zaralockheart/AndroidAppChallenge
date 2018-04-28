package com.example.ucoppp.androidappchallenge.ui.signin

interface SignInView : EmailPasswordValidator {

    fun onEmptyFields()

    suspend fun onSignInUser()

}

interface EmailPasswordValidator {

    // On invalid email format, signup button click should show error. “Email is not valid”
    fun onInvalidEmailFormat(error: String? = "Email is not valid")

    // On invalid password format, signup button click should show error. “Password
    // should contain one special character and minimum 8 character required”
    fun onInvalidPasswordFormat(error: String? = "Password should contain one special character and minimum 8 character required")
}