package com.example.ucoppp.androidappchallenge.util

import android.support.v7.app.AppCompatActivity

fun AppCompatActivity.runOnUi(func: () -> Unit) {
    runOnUiThread{
        func()
    }
}