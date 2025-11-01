package com.jpn.domain.usecase.routine

import com.jpn.domain.model.Routine

interface AddRoutineUseCase {
    suspend operator fun invoke(routine: Routine)
}