package com.jpn.feature.routine.di

import com.jpn.core.utils.managers.LogManager
import com.jpn.core.utils.managers.ResourceManager
import com.jpn.domain.usecase.routine.AddRoutineUseCase
import com.jpn.domain.usecase.routine.GetTodayRoutinesByTypeUseCase
import com.jpn.domain.usecase.routine.UpdateRoutineUseCase
import com.jpn.feature.routine.presentation.dash.RoutinesViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoutineModule {

    @Provides
    fun provideRoutinesViewModel(
        getTodayRoutinesByTypeUseCase: GetTodayRoutinesByTypeUseCase,
        addRoutineUseCase: AddRoutineUseCase,
        updateRoutineUseCase: UpdateRoutineUseCase,
        resourceManager: ResourceManager,
        logManager: LogManager
    ): RoutinesViewModel {
        return RoutinesViewModel(
            getTodayRoutinesByTypeUseCase,
            addRoutineUseCase,
            updateRoutineUseCase,
            resourceManager,
            logManager
        )
    }
}
