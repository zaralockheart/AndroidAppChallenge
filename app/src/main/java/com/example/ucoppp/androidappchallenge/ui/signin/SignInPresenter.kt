package com.example.ucoppp.androidappchallenge.ui.signin

import android.support.v7.app.AppCompatActivity
import com.example.ucoppp.androidappchallenge.R
import com.example.ucoppp.androidappchallenge.ui.base.BasePresenter
import com.example.ucoppp.androidappchallenge.util.isValidEmail
import com.example.ucoppp.androidappchallenge.util.isValidPassword
import io.reactivex.Observable

class SignInPresenter(private val signInView: SignInView?, appCompatActivity: AppCompatActivity?) : BasePresenter(appCompatActivity) {


    fun onUserLogin(email: String, password: String) {

        var failed = false
        Observable.just(email)
                .subscribe({ t ->
                    if (t.isEmpty()) throw Exception(appCompatActivity?.getString(R.string.text_field_empty))
                    if (!t.isValidEmail()) throw Exception(appCompatActivity?.getString(R.string.text_invalid_email))
                }, { error ->
                    signInView?.onInvalidEmailFormat(error = error.localizedMessage)
                    failed = true
                })

        Observable.just(password)
                .subscribe({ t ->
                    if (t.isEmpty()) throw Exception(appCompatActivity?.getString(R.string.text_field_empty))
                    if (!t.isValidPassword()) throw Exception(appCompatActivity?.getString(R.string.text_invalid_password))
                }, { error ->
                    signInView?.onInvalidPasswordFormat(error = error.localizedMessage)
                    failed = true
                })

        if (!failed) {
            signInView?.onSignInUser()
        }
    }
}