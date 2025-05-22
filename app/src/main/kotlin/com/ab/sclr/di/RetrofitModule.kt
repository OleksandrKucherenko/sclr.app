package com.ab.sclr.di

import com.ab.sclr.data.overlays.OverlaysEndpoint
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    fun provideOverlayRetrofit(moshi: Moshi): Retrofit = Retrofit.Builder()
        .baseUrl(OverlaysEndpoint.ENDPOINT)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

}
