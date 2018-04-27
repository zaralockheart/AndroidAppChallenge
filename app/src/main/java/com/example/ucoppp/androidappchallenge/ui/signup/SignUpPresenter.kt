package com.example.ucoppp.androidappchallenge.ui.signup

import dagger.Module
import dagger.Provides
import javax.inject.Inject

class SignUpPresenter (private val signUpview: SignUpview) {

    fun onSignUpUser() {
        signUpview.onSignUpUser()
    }

}