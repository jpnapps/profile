package com.jpn.feature.appearance.di

import com.jpn.core.designsystem.domain.ThemeDataStoreManager
import com.jpn.core.designsystem.domain.ThemePreferenceDataSource
import com.jpn.feature.appearance.data.ThemePreferenceDataSourceImpl
import com.jpn.feature.appearance.data.datasource.ThemeDataStoreManagerImpl
import com.jpn.feature.appearance.data.repository.AppearanceRepositoryImpl
import com.jpn.feature.appearance.data.usecase.GetSelectedThemeUseCaseImpl
import com.jpn.feature.appearance.data.usecase.SaveThemeUseCaseImpl
import com.jpn.feature.appearance.domain.repository.AppearanceRepository
import com.jpn.feature.appearance.domain.usecase.GetSelectedThemeUseCase
import com.jpn.feature.appearance.domain.usecase.SaveThemeUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsModule {

    @Binds
    @Singleton
    abstract fun bindAppearanceRepository(
        impl: AppearanceRepositoryImpl
    ): AppearanceRepository

    @Binds
    @Singleton
    abstract fun bindSaveThemeUseCase(
        impl: SaveThemeUseCaseImpl
    ): SaveThemeUseCase

    @Binds
    @Singleton
    abstract fun bindGetSelectedThemeUseCase(
        impl: GetSelectedThemeUseCaseImpl
    ): GetSelectedThemeUseCase

    @Binds
    @Singleton
    abstract fun bindThemeDataStoreManagerImpl(
        impl: ThemeDataStoreManagerImpl
    ): ThemeDataStoreManager

    @Binds
    @Singleton
    abstract fun bindThemePreferenceDataSourceImpl(
        impl: ThemePreferenceDataSourceImpl
    ): ThemePreferenceDataSource

}
