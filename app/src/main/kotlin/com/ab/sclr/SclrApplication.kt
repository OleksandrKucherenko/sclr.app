package com.ab.sclr

import android.app.Application
import android.content.Context
import coil3.ImageLoader
import coil3.SingletonImageLoader
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.request.crossfade
import dagger.hilt.android.HiltAndroidApp
import okio.Path.Companion.toOkioPath
import timber.log.Timber

@HiltAndroidApp
class SclrApplication : Application(), SingletonImageLoader.Factory {

    override fun onCreate() {
        super.onCreate()

        // configure Timber for DEBUG builds
        Timber.plant(Timber.DebugTree())

        // TODO (olku): reserve for Dependencies injections

        Timber.i("Initialization of the application is completed.")
    }

    override fun newImageLoader(context: Context): ImageLoader {
        val path = context.cacheDir.resolve("image_cache")

        return ImageLoader.Builder(context)
            .crossfade(true)
            .memoryCache {
                MemoryCache.Builder()
                    .maxSizePercent(context, 0.25)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(path.toOkioPath())
                    .maxSizePercent(0.02)
                    .build()
            }
            .build()
    }
}