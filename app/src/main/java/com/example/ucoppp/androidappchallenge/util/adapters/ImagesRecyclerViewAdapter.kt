package com.example.ucoppp.androidappchallenge.util.adapters

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.ucoppp.androidappchallenge.R
import com.example.ucoppp.androidappchallenge.R.id.imageView
import com.example.ucoppp.androidappchallenge.databinding.ItemImageBinding
import com.example.ucoppp.androidappchallenge.model.ImagesModel


class ImagesRecyclerViewAdapter(private val context: Context) : RecyclerView.Adapter<CustomViewHolder>() {

    lateinit var images: MutableList<ImagesModel>

    fun addImagesModel(transactionList: MutableList<ImagesModel>) {
        this.images = transactionList
        notifyDataSetChanged()
    }

    fun clearImagesModel() {
        this.images.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
//        val binding = DataBindingUtil.inflate<ItemImageBinding>(
//                LayoutInflater.from(context),
//                R.layout.item_image,
//                parent,
//                false
//        )
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_image, parent, false)

        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int {
        return images.size
    }
}

class CustomViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

    fun bind(imageModel: ImagesModel) {
//        binding.image = ImagesModel
        val imageView = view.findViewById<ImageView>(imageView)
        Glide.with(view)
                .load(imageModel.imageUrl)
                .into(imageView)
    }
}