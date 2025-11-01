package com.jpn.feature.vault.di

import com.jpn.domain.repository.MediaRepository
import com.jpn.domain.repository.VaultRepository
import com.jpn.feature.vault.data.repository.MediaRepositoryImpl
import com.jpn.feature.vault.data.repository.VaultRepositoryImpl
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
    abstract fun bindVaultRepository(
        impl: VaultRepositoryImpl
    ): VaultRepository

    @Binds
    @Singleton
    abstract fun bindMediaRepository(
        impl: MediaRepositoryImpl
    ): MediaRepository

    /*    @Binds
        @Singleton
        abstract fun bindGetMediaPhotosUseCase(
            impl: GetMediaPhotosUseCaseImpl
        ): GetMediaPhotosUseCase

        @Binds
        @Singleton
        abstract fun bindGetVaultPhotosUseCase(
            impl: GetVaultPhotosUseCaseImpl
        ): GetVaultPhotosUseCase

        @Binds
        @Singleton
        abstract fun bindImportToVaultUseCase(
            impl: ImportToVaultUseCaseImpl
        ): ImportToVaultUseCase*/
}
