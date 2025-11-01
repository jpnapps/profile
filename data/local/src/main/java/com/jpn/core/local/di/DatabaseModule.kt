package com.jpn.core.local.di

import android.content.Context
import androidx.room.Room
import com.jpn.core.local.AppDatabase
import com.jpn.core.local.MIGRATION_2_3
import com.jpn.core.local.MIGRATION_3_4
import com.jpn.core.local.dao.FavoriteDao
import com.jpn.core.local.dao.NoteDao
import com.jpn.core.local.dao.PwdDao
import com.jpn.core.local.dao.RoutineDao
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
        )
             //.addMigrations(MIGRATION_1_2)
            .addMigrations(MIGRATION_2_3)
            .addMigrations(MIGRATION_3_4)
            .build()
    }

    @Provides
    fun provideFavoriteDao(appDatabase: AppDatabase): FavoriteDao =
        appDatabase.favoriteDao()

    @Provides
    fun provideNoteDao(appDatabase: AppDatabase): NoteDao =
        appDatabase.noteDao()

    @Provides
    fun providePwdDao(appDatabase: AppDatabase): PwdDao =
        appDatabase.pwdDao()

    @Provides
    fun provideRoutineDao(appDatabase: AppDatabase): RoutineDao =
        appDatabase.routineDao()
}