package com.example.ucoppp.androidappchallenge.sampledata

import com.example.ucoppp.androidappchallenge.model.ImagesModel

// I think 3 images are enough
fun images() : Array<ImagesModel> =
        arrayOf(ImagesModel("https://images.pexels.com/photos/104827/cat-pet-animal-domestic-104827.jpeg?auto=compress&cs=tinysrgb&h=350"),
                ImagesModel("https://i.ytimg.com/vi/I7jgu-8scIA/maxresdefault.jpg"),
                ImagesModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRPCS-aaaF7r3ypNGiFTi29miHqRFGgC7bnqTs7pw-71L0a-Mwnzw"))
