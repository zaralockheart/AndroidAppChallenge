package com.example.ucoppp.androidappchallenge.ui.signin

import com.example.ucoppp.androidappchallenge.util.isValidEmail
import com.example.ucoppp.androidappchallenge.util.isValidPassword

class SignInPresenter(private val signInView: SignInView) {


    fun onUserLogin(email: String, password: String) {

        if (email.isEmpty() && password.isEmpty()){
            signInView.onEmptyFields()
        }

        if(!email.isValidEmail()){
            signInView.onInvalidEmailFormat()
        }

        if(!password.isValidPassword()){
            signInView.onInvalidPasswordFormat()
        }

        signInView.onSignInUser()
    }
}