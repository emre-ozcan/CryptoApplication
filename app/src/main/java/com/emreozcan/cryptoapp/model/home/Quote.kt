package com.emreozcan.cryptoapp.model.home


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Quote(
    @SerializedName("USD")
    val uSD: USD?
): Parcelable