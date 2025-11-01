package com.jpn.feature.routine.domain

import com.jpn.domain.model.Routine
import com.jpn.domain.repository.RoutineRepository
import com.jpn.domain.usecase.routine.UpdateRoutineUseCase
import javax.inject.Inject

class UpdateRoutineUseCaseImpl @Inject constructor(
    private val repositoryImpl: RoutineRepository
) : UpdateRoutineUseCase {
    override suspend fun invoke(routine: Routine) {
       repositoryImpl.updateRoutine(routine)
    }
}
