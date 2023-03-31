package com.emreozcan.cryptoapp.di

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by @Emre Özcan on 18.04.2022
 */
@HiltAndroidApp
class CryptoApp : Application() {
    companion object {
        private lateinit var instance: CryptoApp
        fun getAppContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }
}