package com.example.ucoppp.androidappchallenge.ui.signin

import com.example.ucoppp.androidappchallenge.util.isValidEmail
import com.example.ucoppp.androidappchallenge.util.isValidPassword

class SignInPresenter(private val signInView: SignInView) {


    suspend fun onUserLogin(email: String, password: String) {

        if (email.isBlank() && password.isBlank()) {
            signInView.onEmptyFields()
            return
        }

//        if (!email.isValidEmail()) {
//            signInView.onInvalidEmailFormat()
//        }
//
//        if (!password.isValidPassword()) {
//            signInView.onInvalidPasswordFormat()
//        }
//
//        if (email.isValidEmail() && password.isValidPassword()) {
            signInView.onSignInUser()
//        }
    }
}