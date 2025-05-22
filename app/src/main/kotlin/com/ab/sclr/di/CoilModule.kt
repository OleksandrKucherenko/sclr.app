package com.ab.sclr.di

import android.content.Context
import coil3.ImageLoader
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient

@Module
@InstallIn(SingletonComponent::class)
object CoilModule {

    @Provides
    fun providesCoil(@ApplicationContext context: Context, okHttpClient: OkHttpClient) =
        ImageLoader.Builder(context).components {
            add(
                OkHttpNetworkFetcherFactory(callFactory = okHttpClient)
            )
        }.build()
}