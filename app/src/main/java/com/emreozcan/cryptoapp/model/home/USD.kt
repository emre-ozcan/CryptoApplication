package com.emreozcan.cryptoapp.model.home


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class USD(
    @SerializedName("fully_diluted_market_cap")
    val fullyDilutedMarketCap: Double?,
    @SerializedName("last_updated")
    val lastUpdated: String?,
    @SerializedName("market_cap")
    val marketCap: Double?,
    @SerializedName("market_cap_dominance")
    val marketCapDominance: Double?,
    @SerializedName("percent_change_1h")
    val percentChange1h: Double?,
    @SerializedName("percent_change_24h")
    val percentChange24h: Double?,
    @SerializedName("percent_change_30d")
    val percentChange30d: Double?,
    @SerializedName("percent_change_60d")
    val percentChange60d: Double?,
    @SerializedName("percent_change_7d")
    val percentChange7d: Double?,
    @SerializedName("percent_change_90d")
    val percentChange90d: Double?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("volume_24h")
    val volume24h: Double?,
    @SerializedName("volume_change_24h")
    val volumeChange24h: Double?
): Parcelable