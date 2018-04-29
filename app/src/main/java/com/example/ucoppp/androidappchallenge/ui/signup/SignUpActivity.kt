package com.example.ucoppp.androidappchallenge.ui.signup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.ucoppp.androidappchallenge.R
import com.example.ucoppp.androidappchallenge.database.user.User
import com.example.ucoppp.androidappchallenge.ui.base.BaseActivity
import com.example.ucoppp.androidappchallenge.util.runOnUi
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

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, SignUpActivity::class.java)

            return intent
        }
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
                    runOnUi(this::signUp)
                }, { _ -> runOnUi(this::signUpFail) })
    }

    override fun onClearError() {
        textInputLayoutEmail.error =""
        textInputLayoutPassword.error =""
        textInputLayoutMobileNumber.error =""
    }

    override fun onEmptyCompulsoryField(error: String?) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun onInvalidMobileNumber(error: String) {
        textInputLayoutMobileNumber.error = error
    }

    override fun onNoGenderSelected() {
        Toast.makeText(this@SignUpActivity, "Please select gender", Toast.LENGTH_LONG).show()
    }

    override fun onInvalidEmailFormat(error: String?) {
        textInputLayoutEmail.error = error
    }

    override fun onInvalidPasswordFormat(error: String?) {
        textInputLayoutPassword.error = error
    }

    private fun signUp() {
        Toast.makeText(this@SignUpActivity, "Sign Up Success!", Toast.LENGTH_LONG).show()
        finish()
    }

    private fun signUpFail() {
        Toast.makeText(this@SignUpActivity, "Email already exist in the system", Toast.LENGTH_LONG).show()
    }
}