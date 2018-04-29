package com.example.ucoppp.androidappchallenge.ui.home

import com.example.ucoppp.androidappchallenge.database.user.User

interface HomeView {

    fun onSignOut()

    fun updateDataToi(user: User?)

    fun onUpdateMobileSuccess()

    fun onUpdateMobileFail(text: String?)
}