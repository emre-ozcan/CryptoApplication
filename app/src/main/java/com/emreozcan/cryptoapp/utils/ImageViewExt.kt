package com.emreozcan.cryptoapp.utils

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import com.emreozcan.cryptoapp.BuildConfig
import com.emreozcan.cryptoapp.di.CryptoApp.Companion.getAppContext

/**
 * Created by @Emre Özcan on 19.04.2022
 */

fun ImageView.loadImage(id: String?) = this.load(BuildConfig.BASE_BASE_URL.plus("$id.png")) {
    crossfade(true)
    crossfade(500)
    placeholder(createPlaceHolder())
}


private fun createPlaceHolder() = CircularProgressDrawable(getAppContext()).apply {
    strokeWidth = 12f
    centerRadius = 40f
    start()
}