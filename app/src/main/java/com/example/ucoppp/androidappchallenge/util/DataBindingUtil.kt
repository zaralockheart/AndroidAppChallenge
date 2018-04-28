package com.example.ucoppp.androidappchallenge.util

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

class DataBindingUtil {

    @BindingAdapter(value = ["imageUrl"])
    fun setGlideAdapter(view: ImageView, imageUrl: String) {

        Glide.with(view)
                .load(imageUrl)
                .into(view)

    }
}