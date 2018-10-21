package com.rick.royalbank

import android.app.Application
import android.content.Context
import com.rick.royalbank.di.appModule
import org.koin.android.ext.android.startKoin

class RoyalBankApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }
}