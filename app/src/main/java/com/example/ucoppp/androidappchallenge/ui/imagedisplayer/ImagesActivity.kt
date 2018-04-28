package com.example.ucoppp.androidappchallenge.ui.imagedisplayer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.example.ucoppp.androidappchallenge.R
import com.example.ucoppp.androidappchallenge.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_images.*


class ImagesActivity : BaseActivity() {

    companion object {

        // Pretty sure I don't need to pass anything to this one
        fun newIntent(context: Context): Intent = Intent(context, ImagesActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_images)

        val layoutManager = GridLayoutManager(this, 2)
        recyclerViewImages.layoutManager = layoutManager
    }
}