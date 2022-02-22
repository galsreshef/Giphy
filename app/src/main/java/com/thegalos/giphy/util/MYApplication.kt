package com.thegalos.giphy.util

import android.app.Application
import com.thegalos.giphy.BuildConfig
import timber.log.Timber

/**
 * Created by Gal Reshef on 2/21/2022.
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}