package com.emreozcan.cryptoapp.network

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by @Emre Özcan on 25.03.2023
 */
class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(request().newBuilder().addHeader("X-CMC_PRO_API_KEY", "e15a2a51-07b1-4d7c-bbff-ae29b8df3b29").build())
    }
}