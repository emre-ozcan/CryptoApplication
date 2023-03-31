package com.emreozcan.cryptoapp.ui.home

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.emreozcan.cryptoapp.base.BaseRepository
import com.emreozcan.cryptoapp.network.ApiFactory
import javax.inject.Inject

/**
 * Created by @Emre Özcan on 19.04.2022
 */
class HomeRepository @Inject constructor(private val apiFactory: ApiFactory) : BaseRepository() {

   fun getData() = Pager(
       config = PagingConfig(
           pageSize = 20,
           maxSize = 100,
           enablePlaceholders = false
       ),
       pagingSourceFactory = {CryptoPagingSource(apiFactory = apiFactory)}
   ).liveData
}