package com.example.ucoppp.androidappchallenge.ui.signin

import android.os.Bundle
import android.text.SpannableStringBuilder
import com.example.ucoppp.androidappchallenge.R
import com.example.ucoppp.androidappchallenge.ui.base.BaseActivity
import com.example.ucoppp.androidappchallenge.ui.home.HomeActivity
import com.example.ucoppp.androidappchallenge.ui.signup.SignUpActivity
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.item_edittext_email.*
import kotlinx.android.synthetic.main.item_edittext_password.*
import kotlinx.coroutines.experimental.async
import javax.inject.Inject

class SignInActivity : BaseActivity(), SignInView {

    @Inject
    lateinit var signInPresenter: SignInPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        signInPresenter = SignInPresenter(this)

        buttonSignIn.setOnClickListener {
            signInPresenter.onUserLogin(
                    email = editTextEmail.text.toString(),
                    password = editTextPassword.text.toString())
        }

        buttonSignUp.setOnClickListener { startActivity(SignUpActivity.newIntent(this)) }

    }

    override fun onResume() {
        super.onResume()
        async {
            val something = userDatabase.userDao().getUserByEmail("@gmaild.com")
            editTextEmail.text = SpannableStringBuilder(something.gender)
        }
    }

    override fun onSignInUser() {
        startActivity(HomeActivity.newIntent(this))
    }

    override fun onInvalidEmailFormat(error: String?) {
        textInputLayoutEmail.error = error
    }

    override fun onInvalidPasswordFormat(error: String?) {
        textInputLayoutPassword.error = error
    }

    override fun onEmptyFields() {
        textInputLayoutEmail.error = "Please fill in Email"
        textInputLayoutPassword.error = "Please fill in Password"
    }
}