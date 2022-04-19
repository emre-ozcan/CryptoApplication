package com.emreozcan.cryptoapp.di

import android.os.Build
import com.emreozcan.cryptoapp.BuildConfig
import com.emreozcan.cryptoapp.network.ApiFactory
import com.emreozcan.cryptoapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.sql.Time
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by @Emre Özcan on 18.04.2022
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpLoggerInterceptor(): HttpLoggingInterceptor{
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG){
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }else httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        return  httpLoggingInterceptor
    }

    @Singleton
    @Provides
    fun provideHttpClint(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient{
        return OkHttpClient.Builder().readTimeout(60,TimeUnit.SECONDS)
            .connectTimeout(60,TimeUnit.SECONDS).addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory{
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(gsonConverterFactory).build()
    }

    @Singleton
    @Provides
    fun provideApiFactory(retrofit: Retrofit): ApiFactory{
        return retrofit.create(ApiFactory::class.java)
    }
}