package com.emreozcan.cryptoapp.model.home


import com.google.gson.annotations.SerializedName

data class CryptoResponse(
    @SerializedName("data")
    val `data`: List<Data>?,
    @SerializedName("status")
    val status: Status?
)