package com.jpn.feature.favorites.di

import com.jpn.feature.favorites.data.local.dao.FavoriteDao
import com.jpn.feature.favorites.data.repository.FavoriteRepositoryImpl
import com.jpn.feature.favorites.domain.repository.FavoriteRepository
import com.jpn.feature.favorites.domain.usecase.AddFavoriteUseCase
import com.jpn.feature.favorites.domain.usecase.GetFavoritesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoriteModule {

    @Provides
    @Singleton
    fun provideFavoriteRepository(
        dao: FavoriteDao
    ): FavoriteRepository {
        return FavoriteRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun provideGetFavoritesUseCase(
        repository: FavoriteRepository
    ): GetFavoritesUseCase {
        return GetFavoritesUseCase(repository)
    }
    @Provides
    @Singleton
    fun provideAddFavoriteUseCase(
        repository: FavoriteRepository
    ): AddFavoriteUseCase {
        return AddFavoriteUseCase(repository)
    }



}