package com.example.ucoppp.androidappchallenge.ui.imagedisplayer

class ImagesPresenter (private val imagesView: ImagesView) {

    fun onLoadImage() {
        imagesView.onLoadImages()
    }
}