package com.emreozcan.cryptoapp.ui.home

import android.widget.ImageView
import android.widget.TextView
import com.emreozcan.cryptoapp.model.home.Data

/**
 * Created by @Emre Özcan on 19.04.2022
 */
interface ItemClickListener {
    fun onItemClick(
        coin: Data, imageView: ImageView, titleTextView: TextView, symbolTextView: TextView
    )
}