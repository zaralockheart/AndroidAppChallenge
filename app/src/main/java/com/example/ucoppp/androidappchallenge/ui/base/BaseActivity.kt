package com.example.ucoppp.androidappchallenge.ui.base

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import com.example.ucoppp.androidappchallenge.database.user.UsersDatabase
import com.example.ucoppp.androidappchallenge.util.APPLICATION

abstract class BaseActivity : AppCompatActivity() {

    protected val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences(APPLICATION, Context.MODE_PRIVATE)
    }

    protected val userDatabase by lazy {
        UsersDatabase.getInstance(this)
    }

    protected val userDao by lazy {
        userDatabase.userDao()
    }
}