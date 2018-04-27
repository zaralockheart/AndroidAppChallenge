package com.example.ucoppp.androidappchallenge.ui.base

import android.support.v7.app.AppCompatActivity
import com.example.ucoppp.androidappchallenge.database.user.UsersDatabase

abstract class BaseActivity : AppCompatActivity() {

    protected val userDatabase by lazy {
        UsersDatabase.getInstance(this)
    }
}