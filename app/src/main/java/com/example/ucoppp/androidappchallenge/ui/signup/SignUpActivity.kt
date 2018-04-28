package com.example.ucoppp.androidappchallenge.ui.signup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.ucoppp.androidappchallenge.R
import com.example.ucoppp.androidappchallenge.database.user.User
import com.example.ucoppp.androidappchallenge.ui.base.BaseActivity
import com.example.ucoppp.androidappchallenge.util.runOnUi
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.item_edittext_email.*
import kotlinx.android.synthetic.main.item_edittext_password.*

class SignUpActivity : BaseActivity(), SignUpview {

    private lateinit var signUpPresenter: SignUpPresenter

    companion object {

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, SignUpActivity::class.java)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        signUpPresenter = SignUpPresenter(this)

        buttonSignUp.setOnClickListener { signUpPresenter.onSignUpUser() }
    }

    override fun onSignUpUser() {

        Observable.fromCallable({
            userDao.insertUser(User(
                    email = editTextEmail.text.toString(),
                    password = editTextPassword.text.toString(),
                    firstName = editTextFirstName.text.toString(),
                    lastName = editTextLastName.text.toString(),
                    mobileNumber = editTextMobileNumber.text.toString(),
                    gender = spinnerGender.selectedItem.toString()))

        }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({
                    runOnUi(this::signUp)
                }, { _ -> runOnUi(this::signUpFail) })
    }


    private fun signUp() {
        Toast.makeText(this@SignUpActivity, "Sign Up Success!", Toast.LENGTH_LONG).show()
        finish()
    }

    private fun signUpFail() {
        Toast.makeText(this@SignUpActivity, "Email already exist in the system", Toast.LENGTH_LONG).show()
    }

    override fun onEmptyCompulsoryField(error: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onInvalidMobileNumber(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNoGenderSelected() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onInvalidEmailFormat(error: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onInvalidPasswordFormat(error: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}