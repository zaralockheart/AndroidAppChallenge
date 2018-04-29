package com.example.ucoppp.androidappchallenge.ui.signup

import com.example.ucoppp.androidappchallenge.database.user.User

class SignUpPresenter(private val signUpview: SignUpview) {

    fun onSignUpUser(user: User) {
        signUpview.onSignUpUser()
    }

}