package com.example.ucoppp.androidappchallenge.ui.signin

interface SignInView {

    fun onEmptyFields()

    fun onInvalidEmailFormat()

    fun onInvalidPasswordFormat()

    fun onSignInUser()

}