package com.jpn.feature.pwd.di

import com.jpn.core.utils.managers.LogManager
import com.jpn.core.utils.managers.TimberLogManager
import com.jpn.domain.profile.repository.PwdRepository
import com.jpn.feature.pwd.data.repository.PwdRepositoryImpl
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
    abstract fun bindPwdRepository(
        impl: PwdRepositoryImpl
    ): PwdRepository


    @Binds
    @Singleton
    abstract fun bindLogManager(
        impl: TimberLogManager
    ): LogManager
}
