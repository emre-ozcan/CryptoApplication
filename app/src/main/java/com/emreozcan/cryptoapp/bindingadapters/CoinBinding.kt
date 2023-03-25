package com.emreozcan.cryptoapp.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.emreozcan.cryptoapp.utils.loadImage

/**
 * Created by @Emre Özcan on 19.04.2022
 */

@BindingAdapter("load_image")
fun loadImage(imageView: ImageView, id: String) {
    imageView.loadImage(id)
}