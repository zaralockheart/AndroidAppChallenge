package com.example.ucoppp.androidappchallenge.ui.signin

import android.support.v7.app.AppCompatActivity
import com.example.ucoppp.androidappchallenge.ui.base.BasePresenter
import com.example.ucoppp.androidappchallenge.util.isValidEmail
import com.example.ucoppp.androidappchallenge.util.isValidPassword
import com.example.ucoppp.androidappchallenge.util.runOnUi
import io.reactivex.Observable

class SignInPresenter(private val signInView: SignInView, appCompatActivity: AppCompatActivity) : BasePresenter(appCompatActivity) {


    fun onUserLogin(email: String, password: String) {

        appCompatActivity.runOnUi {
            var failed = false
            Observable.just(email)
                    .subscribe({ t ->
                        if (t.isEmpty()) throw Exception("Email is empty")
                        if (!t.isValidEmail()) throw Exception("Invalid Email")
                    }, { error ->
                        signInView.onInvalidEmailFormat(error = error.localizedMessage)
                        failed= true
                    })

            Observable.just(password)
                    .subscribe({ t ->
                        if (t.isEmpty()) throw Exception("Password is empty")
                        if (!t.isValidPassword()) throw Exception("Invalid Password")
                    }, { error ->
                        signInView.onInvalidPasswordFormat(error = error.localizedMessage)
                        failed= true
                    })

            if(!failed){
                signInView.onSignInUser()
            }
        }
    }
}