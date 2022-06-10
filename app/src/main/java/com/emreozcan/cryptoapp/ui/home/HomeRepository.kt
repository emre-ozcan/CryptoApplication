package com.emreozcan.cryptoapp.ui.home

import com.emreozcan.cryptoapp.base.BaseRepository
import com.emreozcan.cryptoapp.network.ApiFactory
import javax.inject.Inject

/**
 * Created by @Emre Özcan on 19.04.2022
 */
class HomeRepository @Inject constructor(private val apiFactory: ApiFactory) : BaseRepository() {

    suspend fun getData(
        apiKey: String,
        limit: String
    ) = safeApiRequest {
        apiFactory.getData(apiKey, limit)
    }
}