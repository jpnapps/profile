package com.jpn.core.local.di

import android.content.Context
import androidx.room.Room
import com.jpn.core.local.AppDatabase
import com.jpn.core.local.dao.FavoriteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "jpn.db"
        ).build()
    }

    @Provides
    fun provideFavoriteDao(appDatabase: AppDatabase): FavoriteDao =
        appDatabase.favoriteDao()
}