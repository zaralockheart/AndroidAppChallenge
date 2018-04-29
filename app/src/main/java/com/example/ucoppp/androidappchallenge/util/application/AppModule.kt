package com.example.ucoppp.androidappchallenge.util.application

import android.content.Context
import android.content.SharedPreferences
import com.example.ucoppp.androidappchallenge.util.APPLICATION
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule (private val appChallengeApplication: AndroidAppChallengeApplication){

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = appChallengeApplication

    @Provides
    @Singleton
    fun provideSharedPreference(context: Context): SharedPreferences = context.getSharedPreferences(APPLICATION, Context.MODE_PRIVATE)
}