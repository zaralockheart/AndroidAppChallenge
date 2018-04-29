package com.example.ucoppp.androidappchallenge.util.application

import android.support.v7.app.AppCompatActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(appChallengeApplication: AndroidAppChallengeApplication)

    fun inject(appCompatActivity: AppCompatActivity)
}