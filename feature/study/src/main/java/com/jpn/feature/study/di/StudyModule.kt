package com.jpn.feature.study.di

import com.jpn.domain.usecase.study.GetStudyNotesUseCase
import com.jpn.feature.study.presentation.viewmodel.StudyNotesScreenViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object StudyModule {

    @Provides
    fun provideStudyNotesScreenViewModel(
        getStudyNotesUseCase: GetStudyNotesUseCase,
    ): StudyNotesScreenViewModel {
        return StudyNotesScreenViewModel(
            getStudyNotesUseCase
        )
    }
}
