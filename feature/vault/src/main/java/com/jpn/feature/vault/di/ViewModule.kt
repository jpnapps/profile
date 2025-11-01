package com.jpn.feature.vault.di

import com.jpn.domain.usecase.vault.GetMediaPhotosUseCase
import com.jpn.domain.usecase.vault.GetVaultPhotosUseCase
import com.jpn.domain.usecase.vault.ImportToVaultUseCase
import com.jpn.feature.vault.domain.GetMediaPhotosUseCaseImpl
import com.jpn.feature.vault.domain.GetVaultPhotosUseCaseImpl
import com.jpn.feature.vault.domain.ImportToVaultUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModule {

    @Binds
    //@Singleton
    abstract fun bindGetMediaPhotosUseCase(
        impl: GetMediaPhotosUseCaseImpl
    ): GetMediaPhotosUseCase

    @Binds
    //@Singleton
    abstract fun bindGetVaultPhotosUseCase(
        impl: GetVaultPhotosUseCaseImpl
    ): GetVaultPhotosUseCase

    @Binds
    //@Singleton
    abstract fun bindImportToVaultUseCase(
        impl: ImportToVaultUseCaseImpl
    ): ImportToVaultUseCase
}
