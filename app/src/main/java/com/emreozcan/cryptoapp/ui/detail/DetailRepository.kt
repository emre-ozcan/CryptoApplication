package com.emreozcan.cryptoapp.ui.detail

import com.emreozcan.cryptoapp.base.BaseRepository
import com.emreozcan.cryptoapp.network.ApiFactory
import javax.inject.Inject

/**
 * Created by @Emre Özcan on 19.04.2022
 */
class DetailRepository @Inject constructor(private val apiFactory: ApiFactory) : BaseRepository() {
    suspend fun getDetail(
        symbol: String
    ) = safeApiRequest {
        apiFactory.getDetail(symbol)
    }
}