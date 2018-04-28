package com.example.ucoppp.androidappchallenge.ui.signin

import android.os.Bundle
import android.widget.Toast
import com.example.ucoppp.androidappchallenge.R
import com.example.ucoppp.androidappchallenge.database.user.UsersDatabase
import com.example.ucoppp.androidappchallenge.ui.base.BaseActivity
import com.example.ucoppp.androidappchallenge.ui.home.HomeActivity
import com.example.ucoppp.androidappchallenge.ui.signup.SignUpActivity
import com.example.ucoppp.androidappchallenge.util.runOnUi
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.item_edittext_email.*
import kotlinx.android.synthetic.main.item_edittext_password.*
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class SignInActivity : BaseActivity(), SignInView {

    @Inject
    lateinit var signInPresenter: SignInPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        signInPresenter = SignInPresenter(this)

        buttonSignIn.setOnClickListener {
            launch {
                signInPresenter.onUserLogin(
                        email = editTextEmail.text.toString(),
                        password = editTextPassword.text.toString())
            }
        }

        buttonSignUp.setOnClickListener { startActivity(SignUpActivity.newIntent(this)) }

    }

    override suspend fun onSignInUser() {

        Observable.just(userDatabase)
                .subscribeOn(Schedulers.io())
                .subscribe { db: UsersDatabase? ->
                    val finalUser = db?.userDao()?.getUserByEmail(editTextEmail.text.toString())
                    if (finalUser?.password == editTextPassword.text.toString()) {

                        runOnUi { navigateToHome(finalUser.userID) }

                    } else {

                        runOnUi { this.showToast("Not sign in") }
                    }
                }
    }


    fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    fun navigateToHome(userid: String) {
        startActivity(HomeActivity.newIntent(this, userId = userid))
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