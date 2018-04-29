package com.example.ucoppp.androidappchallenge.ui.base

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.ucoppp.androidappchallenge.database.user.UsersDatabase
import com.example.ucoppp.androidappchallenge.util.APPLICATION
import com.example.ucoppp.androidappchallenge.util.application.DaggerAppComponent

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerAppComponent.builder().build().inject(appCompatActivity = this@BaseActivity)
    }
}