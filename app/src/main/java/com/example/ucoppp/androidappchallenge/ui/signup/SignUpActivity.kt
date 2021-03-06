package com.example.ucoppp.androidappchallenge.ui.signup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.ucoppp.androidappchallenge.R
import com.example.ucoppp.androidappchallenge.database.user.User
import com.example.ucoppp.androidappchallenge.ui.base.BaseActivity
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.item_edittext_email.*
import kotlinx.android.synthetic.main.item_edittext_mobile.*
import kotlinx.android.synthetic.main.item_edittext_password.*

class SignUpActivity : BaseActivity(), SignUpview {

    private lateinit var signUpPresenter: SignUpPresenter

    lateinit var user: User

    companion object {

        // Nope, not passing anything here

        fun newIntent(context: Context): Intent = Intent(context, SignUpActivity::class.java)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        signUpPresenter = SignUpPresenter(this, this)
    }

    fun onClickSignUp(view: View) {
        user = User(
                email = editTextEmail.text.toString(),
                password = editTextPassword.text.toString(),
                firstName = editTextFirstName.text.toString(),
                lastName = editTextLastName.text.toString(),
                mobileNumber = editTextMobileNumber.text.toString(),
                gender = spinnerGender.selectedItem.toString())

        signUpPresenter.onSignUpUser(
                user = user
        )
    }


    override fun onSignUpUser() {

        Observable.fromCallable({
            userDao.insertUser(user)

        }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({
                    runOnUiThread(this::signUp)
                }, { _ -> runOnUiThread(this::signUpFail) })
    }

    override fun onClearError() {
        textInputLayoutEmail.error = ""
        textInputLayoutPassword.error = ""
        textInputLayoutMobileNumber.error = ""
        textInputLayoutGender.error = ""
    }

    override fun onEmptyCompulsoryField(error: String?) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun onInvalidMobileNumber(error: String) {
        textInputLayoutMobileNumber.error = error
    }

    override fun onNoGenderSelected(error: String?) {
        textInputLayoutGender.error = error
    }

    override fun onInvalidEmailFormat(error: String?) {
        textInputLayoutEmail.error = error
    }

    override fun onInvalidPasswordFormat(error: String?) {
        textInputLayoutPassword.error = error
    }

    private fun signUp() {
        Toast.makeText(this@SignUpActivity, R.string.text_signin_success, Toast.LENGTH_LONG).show()
        finish()
    }

    private fun signUpFail() {
        Toast.makeText(this@SignUpActivity, R.string.text_email_exist, Toast.LENGTH_LONG).show()
    }
}