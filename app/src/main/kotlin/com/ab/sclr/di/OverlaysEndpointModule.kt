package com.ab.sclr.di

import com.ab.sclr.domain.overlays.OverlaysEndpoint
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object OverlaysEndpointModule {

    @Provides
    fun provideOverlaysClient(@DefaultClient retrofit: Retrofit): OverlaysEndpoint =
        retrofit.create(OverlaysEndpoint::class.java)
}