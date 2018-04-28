package com.example.ucoppp.androidappchallenge.ui.home

class HomePresenter(private val homeView: HomeView) {

    fun onClickEditMobile() {
        homeView.onClickEditMobile()
    }

    fun onClickImages() {
        homeView.onClickImages()
    }
}