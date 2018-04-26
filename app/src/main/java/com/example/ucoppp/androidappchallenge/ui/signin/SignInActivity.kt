package com.example.ucoppp.androidappchallenge.ui.signin

import android.os.Bundle
import com.example.ucoppp.androidappchallenge.R
import com.example.ucoppp.androidappchallenge.ui.base.BaseActivity
import com.example.ucoppp.androidappchallenge.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_sign_in.*
import javax.inject.Inject

class SignInActivity : BaseActivity(), SignInView {

    @Inject
    lateinit var signInPresenter: SignInPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        signInPresenter = SignInPresenter(this)

        buttonSignIn.setOnClickListener { signInPresenter.onUserLogin(
                email = editTextEmail.text.toString(),
                password = editTextPassword.text.toString())
        }

    }

    override fun onSignInUser() {
        startActivity(HomeActivity.newIntent(this))
    }

    override fun onInvalidEmailFormat(error: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onInvalidPasswordFormat(error: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onEmptyFields() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}