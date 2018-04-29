package com.example.ucoppp.androidappchallenge.util.application

import android.app.Application

class AndroidAppChallengeApplication : Application() {


    val component: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()

        component.inject(this)
    }
}