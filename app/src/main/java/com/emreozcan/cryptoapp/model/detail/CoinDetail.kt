package com.emreozcan.cryptoapp.model.detail


import com.google.gson.annotations.SerializedName

data class CoinDetail(
    @SerializedName("category")
    val category: String?,
    @SerializedName("contract_address")
    val contractAddress: List<ContractAddress>?,
    @SerializedName("date_added")
    val dateAdded: String?,
    @SerializedName("date_launched")
    val dateLaunched: Any?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("is_hidden")
    val isHidden: Int?,
    @SerializedName("logo")
    val logo: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("notice")
    val notice: String?,
    @SerializedName("platform")
    val platform: Any?,
    @SerializedName("self_reported_circulating_supply")
    val selfReportedCirculatingSupply: Any?,
    @SerializedName("self_reported_market_cap")
    val selfReportedMarketCap: Any?,
    @SerializedName("self_reported_tags")
    val selfReportedTags: Any?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("subreddit")
    val subreddit: String?,
    @SerializedName("symbol")
    val symbol: String?,
    @SerializedName("tag-groups")
    val tagGroups: List<String>?,
    @SerializedName("tag-names")
    val tagNames: List<String>?,
    @SerializedName("tags")
    val tags: List<String>?,
    @SerializedName("twitter_username")
    val twitterUsername: String?,
    @SerializedName("urls")
    val urls: Urls?
)