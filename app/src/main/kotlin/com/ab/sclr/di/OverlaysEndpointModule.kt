package com.ab.sclr.di

import com.ab.sclr.data.overlays.OverlaysEndpoint
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object OverlaysEndpointModule {

    @Provides
    fun provideOverlaysClient(retrofit: Retrofit): OverlaysEndpoint =
        retrofit.create(OverlaysEndpoint::class.java)
}