package com.jpn.feature.notes.di

import com.jpn.core.local.dao.PwdDao
import com.jpn.domain.profile.repository.PwdRepository
import com.jpn.domain.profile.usecase.AddPwdUseCase
import com.jpn.domain.profile.usecase.GetPwdByIdUseCase
import com.jpn.domain.profile.usecase.GetPwdsUseCase
import com.jpn.domain.profile.usecase.UpdatePwdUseCase
import com.jpn.feature.favorites.data.repository.PwdRepositoryImpl
import com.jpn.feature.notes.presentation.list.PwdsViewModel
import com.jpn.feature.pwd.presentation.detail.PwdDetailViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PwdModule {
    @Provides
    @Singleton
    fun providePwdRepository(
        pwdDao: PwdDao
    ): PwdRepository {
        return PwdRepositoryImpl(pwdDao)
    }

    @Provides
    @Singleton
    fun provideGetPwdsUseCase(
        repository: PwdRepository
    ): GetPwdsUseCase {
        return GetPwdsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideAddPwdUseCase(
        repository: PwdRepository
    ): AddPwdUseCase {
        return AddPwdUseCase(repository)
    }

    @Provides
    fun providePwdsViewModel(
        getPwdUseCase: GetPwdsUseCase,
        addPwdUseCase: AddPwdUseCase
    ): PwdsViewModel {
        return PwdsViewModel(getPwdUseCase, addPwdUseCase)
    }

    @Provides
    @Singleton
    fun provideUpdatePwdUseCase(
        repository: PwdRepository
    ): UpdatePwdUseCase {
        return UpdatePwdUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetPwdByIdUseCase(
        repository: PwdRepository
    ): GetPwdByIdUseCase {
        return GetPwdByIdUseCase(repository)
    }

    @Provides
    fun providePwdDetailViewModel(
        getPwdByIdUseCase: GetPwdByIdUseCase,
       updatePwdUseCase: UpdatePwdUseCase
    ): PwdDetailViewModel {
        return PwdDetailViewModel(getPwdByIdUseCase, updatePwdUseCase)
    }
}
