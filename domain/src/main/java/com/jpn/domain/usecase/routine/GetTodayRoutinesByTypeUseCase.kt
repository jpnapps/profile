package com.jpn.domain.usecase.routine

import com.jpn.domain.model.Routine
import com.jpn.domain.model.RoutineType
import kotlinx.coroutines.flow.Flow

interface GetTodayRoutinesByTypeUseCase {
    operator fun invoke(type: RoutineType): Flow<List<Routine>>
}