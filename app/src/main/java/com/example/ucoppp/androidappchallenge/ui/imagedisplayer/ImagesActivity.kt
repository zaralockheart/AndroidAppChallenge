package com.example.ucoppp.androidappchallenge.ui.imagedisplayer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import com.example.ucoppp.androidappchallenge.R
import com.example.ucoppp.androidappchallenge.model.ImagesModel
import com.example.ucoppp.androidappchallenge.sampledata.images
import com.example.ucoppp.androidappchallenge.ui.base.BaseActivity
import com.example.ucoppp.androidappchallenge.util.adapters.ImagesRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_images.*
import java.util.*


class ImagesActivity : BaseActivity(), ImagesView {


    private lateinit var imagesPresenter: ImagesPresenter

    lateinit var adapter: ImagesRecyclerViewAdapter

    companion object {

        // Pretty sure I don't need to pass anything to this one
        fun newIntent(context: Context): Intent = Intent(context, ImagesActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_images)

        imagesPresenter = ImagesPresenter(this)
        adapter = ImagesRecyclerViewAdapter(this)
        imagesPresenter.onLoadImage()

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val layoutManager = GridLayoutManager(this, 2)
        recyclerViewImages.layoutManager = layoutManager
        recyclerViewImages.adapter = adapter

    }

    override fun onLoadImages() {
        val list: MutableList<ImagesModel> = mutableListOf()
        val random = Random()
        val array = Array(100, {})
        array.map {
            list.add(images()[random.nextInt(3)])
        }
        adapter.addImagesModel(list)
    }
}