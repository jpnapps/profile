package com.jpn.feature.study.di

import com.jpn.domain.repository.StudyRepository
import com.jpn.domain.usecase.study.GetStudyNotesUseCase
import com.jpn.feature.study.data.repository.StudyRepositoryImpl
import com.jpn.feature.study.domain.GetStudyNotesUseCaseImpl
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
    abstract fun bindStudyRepository(
        impl: StudyRepositoryImpl
    ): StudyRepository

    @Binds
    @Singleton
    abstract fun bindGetStudyNotesUseCaseImpl(
        impl: GetStudyNotesUseCaseImpl
    ): GetStudyNotesUseCase
}