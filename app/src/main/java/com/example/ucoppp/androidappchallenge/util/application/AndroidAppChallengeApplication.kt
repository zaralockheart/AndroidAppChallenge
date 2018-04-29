package com.example.ucoppp.androidappchallenge.util.application

import android.app.Application

class AndroidAppChallengeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder().build().inject(appChallengeApplication = this@AndroidAppChallengeApplication)

    }
}