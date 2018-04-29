package com.example.ucoppp.androidappchallenge.ui.signup

import android.support.v7.app.AppCompatActivity
import com.example.ucoppp.androidappchallenge.R
import com.example.ucoppp.androidappchallenge.database.user.User
import com.example.ucoppp.androidappchallenge.ui.base.BasePresenter
import com.example.ucoppp.androidappchallenge.util.*
import io.reactivex.Observable

class SignUpPresenter(private val signUpView: SignUpview, appCompatActivity: AppCompatActivity) : BasePresenter(appCompatActivity) {

    fun onSignUpUser(user: User) {

        var failed = true


        Observable.just(user.email)
                .subscribe({
                    if (it.isEmpty())
                        throw EmptyPrimaryFieldsException(appCompatActivity.getString(R.string.text_data_empty))

                    if (!it.isValidEmail())
                        throw Exception(appCompatActivity.getString(R.string.text_invalid_email))
                }, { error ->
                    if (error is EmptyPrimaryFieldsException) {
                        signUpView.onEmptyCompulsoryField(error = error.localizedMessage)
                    } else {
                        signUpView.onInvalidEmailFormat(error = error.localizedMessage)
                    }
                })

        Observable.just(user.password)
                .subscribe({
                    if (it.isEmpty()) {
                        throw EmptyPrimaryFieldsException(appCompatActivity.getString(R.string.text_data_empty))
                    }

                    if (!it.isValidPassword()) throw Exception(appCompatActivity.getString(R.string.text_invalid_password))
                }, { error ->

                    if (error is EmptyPrimaryFieldsException) {
                        signUpView.onEmptyCompulsoryField(error = error.localizedMessage)
                    } else {
                        signUpView.onInvalidPasswordFormat(error = error.localizedMessage)
                    }
                })

        Observable.just(user.mobileNumber)
                .subscribe({
                    if (it.isEmpty()) {
                        throw EmptyPrimaryFieldsException(appCompatActivity.getString(R.string.text_data_empty))
                    }

                    if (!it.isValidMobileNumber()) throw Exception(appCompatActivity.getString(R.string.text_invalid_mobile))
                }, { error ->

                    if (error is EmptyPrimaryFieldsException) {
                        signUpView.onEmptyCompulsoryField(error = error.localizedMessage)
                    } else {
                        signUpView.onInvalidMobileNumber(error = error.localizedMessage)
                    }
                })

        Observable.just(user.gender)
                .doOnNext { t ->
                    if (t.isEmpty()) {
                        throw EmptyPrimaryFieldsException(appCompatActivity.getString(R.string.text_data_empty))
                    }
                }
                .doAfterNext { t -> if (!t.isValidEmail()) throw Exception(appCompatActivity.getString(R.string.text_invalid_email)) }
                .subscribe({

                }, { error ->

                    if (error is EmptyPrimaryFieldsException) {
                        signUpView.onEmptyCompulsoryField(error = error.localizedMessage)
                    }
                    failed = true
                })


        if (!failed) signUpView.onSignUpUser()
    }

}