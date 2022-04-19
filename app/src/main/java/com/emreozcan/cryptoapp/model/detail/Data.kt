package com.emreozcan.cryptoapp.model.detail


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("ETH")
    val eTH: List<CoinDetail>?
)