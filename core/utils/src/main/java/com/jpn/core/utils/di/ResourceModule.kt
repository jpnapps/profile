package com.jpn.core.local.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jpn.core.utils.managers.ResourceManager
import com.jpn.core.utils.managers.ResourceManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ResourceModule {

    @Provides
    @Singleton
    fun provideResourceManager(
        @ApplicationContext context: Context
    ): ResourceManager = ResourceManagerImpl(context)

    @Provides
    @Singleton
    fun provideGson(
    ): Gson = GsonBuilder().setPrettyPrinting().create()
}