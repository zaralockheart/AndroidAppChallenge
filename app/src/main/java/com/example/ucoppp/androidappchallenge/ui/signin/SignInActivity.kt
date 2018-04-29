package com.example.ucoppp.androidappchallenge.ui.signin

import android.os.Bundle
import android.widget.Toast
import com.example.ucoppp.androidappchallenge.R
import com.example.ucoppp.androidappchallenge.database.user.UsersDatabase
import com.example.ucoppp.androidappchallenge.ui.base.BaseActivity
import com.example.ucoppp.androidappchallenge.ui.home.HomeActivity
import com.example.ucoppp.androidappchallenge.ui.signup.SignUpActivity
import com.example.ucoppp.androidappchallenge.util.APP_SIGN_IN
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.item_edittext_email.*
import kotlinx.android.synthetic.main.item_edittext_password.*
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class SignInActivity : BaseActivity(), SignInView {


    private val userId by lazy {
        sharedPreferences.getString(APP_SIGN_IN, "")
    }

    @Inject
    lateinit var signInPresenter: SignInPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        goToHomeIfUserSignedIn()

        setContentView(R.layout.activity_sign_in)

        signInPresenter = SignInPresenter(this, this)

        setUpUiInteractions()


    }

    override fun onSignInUser() {

        Observable.just(userDatabase)
                .subscribeOn(Schedulers.io())
                .subscribe { db: UsersDatabase? ->
                    val finalUser = db?.userDao()?.getUserByEmail(editTextEmail.text.toString())

                    runOnUiThread {

                        if (finalUser?.password == editTextPassword.text.toString()) {

                            navigateToHome(finalUser.userID)

                        } else {

                            this.showToast(text = R.string.text_fail_signin)
                        }
                    }
                }
    }

    override fun onClearError() {
        textInputLayoutEmail.error = ""
        textInputLayoutPassword.error = ""
    }

    override fun onInvalidEmailFormat(error: String?) {
        textInputLayoutEmail.error = error
    }

    override fun onInvalidPasswordFormat(error: String?) {
        textInputLayoutPassword.error = error
    }

    private fun showToast(text: Int) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    private fun navigateToHome(userid: String) {
        startActivity(HomeActivity.newIntent(this, userId = userid))
    }

    private fun goToHomeIfUserSignedIn() {
        if (userId.isNotBlank()) {
            navigateToHome(userId)
        }
    }

    private fun setUpUiInteractions() {
        buttonSignIn.setOnClickListener {
            launch {
                signInPresenter.onUserLogin(
                        email = editTextEmail.text.toString(),
                        password = editTextPassword.text.toString())
            }
        }

        buttonSignUp.setOnClickListener { startActivity(SignUpActivity.newIntent(this)) }
    }
}