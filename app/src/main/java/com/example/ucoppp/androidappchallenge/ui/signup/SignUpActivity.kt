package com.example.ucoppp.androidappchallenge.ui.signup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.ucoppp.androidappchallenge.R
import com.example.ucoppp.androidappchallenge.database.user.User
import com.example.ucoppp.androidappchallenge.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.item_edittext_email.*
import kotlinx.coroutines.experimental.async

class SignUpActivity : BaseActivity(), SignUpview {

    lateinit var signUpPresenter: SignUpPresenter

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

        async {
            userDatabase.userDao().insertUser(User(
                    email = editTextEmail.text.toString(),
                    firstName = editTextFirstName.text.toString(),
                    lastName = editTextLastName.text.toString(),
                    mobileNumber = editTextMobileNumber.text.toString(),
                    gender = spinnerGender.selectedItem.toString()
            ))
        }
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