package com.emreozcan.cryptoapp.network

import com.emreozcan.cryptoapp.model.detail.DetailResponse
import com.emreozcan.cryptoapp.model.home.CryptoResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by @Emre Özcan on 18.04.2022
 */
interface ApiFactory {
    @GET("v1/cryptocurrency/listings/latest")
    suspend fun getData(
        @Query("limit") limit: Int,
        @Query("start") start: Int
    ): CryptoResponse

    @GET("v2/cryptocurrency/info")
    suspend fun getDetail(
        @Query("symbol") symbol: String
    ): DetailResponse
}