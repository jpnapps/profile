package com.jpn.feature.profile.di

import com.jpn.domain.profile.repository.ProfileRepository
import com.jpn.domain.profile.usecase.GetProfileUseCase
import com.jpn.feature.profile.data.repository.ProfileRepositoryImpl
import com.jpn.feature.profile.presentation.viewmodel.ProfileViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProfileModule {
 /*   @Provides
    @Singleton
    fun provideProfileRepository(
        profileDao: ProfileDao
    ): ProfileRepository {
        return ProfileRepositoryImpl(profileDao)
    }*/
    @Provides
    @Singleton
    fun provideProfileRepository(
    ): ProfileRepository {
        return ProfileRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideGetProfileUseCase(
        repository: ProfileRepository
    ): GetProfileUseCase {
        return GetProfileUseCase(repository)
    }

    @Provides
    fun provideProfileViewModel(
        getProfileUseCase: GetProfileUseCase,
        //updateProfileUseCase: UpdateProfileUseCase
    ): ProfileViewModel {
        return ProfileViewModel(getProfileUseCase)
    }

   /* @Provides
    @Singleton
    fun provideUpdateProfileUseCase(
        repository: ProfileRepository
    ): UpdateProfileUseCase {
        return UpdateProfileUseCase(repository)
    }*/
}
