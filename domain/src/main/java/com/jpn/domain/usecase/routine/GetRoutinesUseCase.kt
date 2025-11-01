package com.jpn.domain.usecase.routine

import com.jpn.domain.model.Routine
import kotlinx.coroutines.flow.Flow

interface GetRoutinesUseCase {
    operator fun invoke(): Flow<List<Routine>>
}