package com.ab.sclr.di

import android.content.Context
import com.ab.sclr.data.SavedProjectsRepository
import com.ab.sclr.data.SavedProjectsRepositoryImpl
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideSavedProjectsRepository(
        @ApplicationContext context: Context,
        moshi: Moshi
    ): SavedProjectsRepository {
        return SavedProjectsRepositoryImpl(context, moshi)
    }
}