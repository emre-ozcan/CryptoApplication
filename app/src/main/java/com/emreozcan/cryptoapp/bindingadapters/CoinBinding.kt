package com.emreozcan.cryptoapp.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.emreozcan.cryptoapp.utils.loadImage

/**
 * Created by @Emre Özcan on 19.04.2022
 */
class CoinBinding {

    companion object{
        //https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png

        @BindingAdapter("load_image")
        @JvmStatic
        fun loadImage(imageView: ImageView, coinImage: String){
            val imageUrl = "https://s2.coinmarketcap.com/static/img/coins/64x64/$coinImage.png"
            imageView.loadImage(imageUrl)
        }
    }
}