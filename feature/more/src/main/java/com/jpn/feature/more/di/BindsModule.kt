package com.jpn.feature.more.di

import com.jpn.domain.repository.MoreRepository
import com.jpn.domain.usecase.GetMoreMenuItemsUseCase
import com.jpn.feature.more.data.repository.MoreRepositoryImpl
import com.jpn.feature.more.domain.GetMoreMenuItemsUseCaseImpl
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
    abstract fun bindMoreRepositoryImpl(
        impl: MoreRepositoryImpl
    ): MoreRepository

    @Binds
    @Singleton
    abstract fun bindGetMoreMenuItemsUseCaseImpl(
        impl: GetMoreMenuItemsUseCaseImpl
    ): GetMoreMenuItemsUseCase
}
