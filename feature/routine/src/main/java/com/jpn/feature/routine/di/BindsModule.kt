package com.jpn.feature.routine.di

import com.jpn.domain.repository.RoutineRepository
import com.jpn.domain.usecase.routine.AddRoutineUseCase
import com.jpn.domain.usecase.routine.GetTodayRoutinesByTypeUseCase
import com.jpn.domain.usecase.routine.UpdateRoutineUseCase
import com.jpn.feature.routine.data.repository.RoutineRepositoryImpl
import com.jpn.feature.routine.domain.AddRoutineUseCaseImpl
import com.jpn.feature.routine.domain.GetTodayRoutinesByTypeUseCaseImpl
import com.jpn.feature.routine.domain.UpdateRoutineUseCaseImpl
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
    abstract fun bindRoutineRepository(
        impl: RoutineRepositoryImpl
    ): RoutineRepository

    @Binds
    @Singleton
    abstract fun bindGetTodayRoutinesByTypeUseCase(
        impl: GetTodayRoutinesByTypeUseCaseImpl
    ): GetTodayRoutinesByTypeUseCase

    @Binds
    @Singleton
    abstract fun bindAddRoutineUseCase(
        impl: AddRoutineUseCaseImpl
    ): AddRoutineUseCase

    @Binds
    @Singleton
    abstract fun bindUpdateRoutineUseCase(
        impl: UpdateRoutineUseCaseImpl
    ): UpdateRoutineUseCase
}
