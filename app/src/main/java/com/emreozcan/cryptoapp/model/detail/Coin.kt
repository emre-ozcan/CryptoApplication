package com.emreozcan.cryptoapp.model.detail


import com.google.gson.annotations.SerializedName

data class Coin(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("symbol")
    val symbol: String?
)