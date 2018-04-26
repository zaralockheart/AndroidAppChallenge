package com.example.ucoppp.androidappchallenge.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.ucoppp.androidappchallenge.R
import com.example.ucoppp.androidappchallenge.ui.base.BaseActivity

class HomeActivity : BaseActivity() {

    companion object {

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, HomeActivity::class.java)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


    }
}