package com.jpn.domain.usecase.routine

import com.jpn.domain.model.Routine

interface DeleteRoutineUseCase {
    suspend operator fun invoke(routine: Routine)
}