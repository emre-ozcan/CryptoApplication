package com.emreozcan.cryptoapp.ui.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import coil.network.HttpException
import com.emreozcan.cryptoapp.R
import com.emreozcan.cryptoapp.di.CryptoApp.Companion.getAppContext
import com.emreozcan.cryptoapp.model.home.CryptoModel
import com.emreozcan.cryptoapp.network.ApiFactory
import com.emreozcan.cryptoapp.utils.Constants
import java.io.IOException

/**
 * Created by @Emre Özcan on 31.03.2023
 */

class CryptoPagingSource(
    private val apiFactory: ApiFactory,
) : PagingSource<Int, CryptoModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CryptoModel> {
        val position = params.key ?: Constants.STARTING_PAGE_INDEX

        return try {
            val offsetForPagination = (position - 1) * 20 + 1
            val response = apiFactory.getData(params.loadSize, offsetForPagination)
            val cryptoModels = response.data

            LoadResult.Page(
                data = cryptoModels,
                prevKey = if (position == Constants.STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (cryptoModels.isNotEmpty()) position + 1 else null
            )
        } catch (exception: IOException) {
            LoadResult.Error(Throwable(getAppContext().getString(R.string.error_message_no_internet_connection)))
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, CryptoModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
