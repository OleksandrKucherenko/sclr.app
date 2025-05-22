package com.ab.sclr

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class SclrApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // configure Timber for DEBUG builds
        Timber.plant(Timber.DebugTree())

        // TODO (olku): reserve for Dependencies injections

        Timber.i("Initialization of the application is completed.")
    }
}