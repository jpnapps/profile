package com.jpn.feature.routine.domain

import com.jpn.domain.model.Routine
import com.jpn.domain.repository.RoutineRepository
import com.jpn.domain.usecase.routine.AddRoutineUseCase
import javax.inject.Inject

class AddRoutineUseCaseImpl @Inject constructor(
    private val repositoryImpl: RoutineRepository
) : AddRoutineUseCase {
    override suspend fun invoke(routine: Routine) {
       repositoryImpl.addRoutine(routine)
    }
}
