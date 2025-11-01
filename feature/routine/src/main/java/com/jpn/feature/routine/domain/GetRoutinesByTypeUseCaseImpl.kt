package com.jpn.feature.routine.domain

import com.jpn.domain.model.Routine
import com.jpn.domain.model.RoutineType
import com.jpn.domain.usecase.routine.GetTodayRoutinesByTypeUseCase
import com.jpn.feature.routine.data.repository.RoutineRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTodayRoutinesByTypeUseCaseImpl @Inject constructor(
    private val repositoryImpl: RoutineRepositoryImpl
) : GetTodayRoutinesByTypeUseCase {
    override fun invoke(type: RoutineType): Flow<List<Routine>> {
        return repositoryImpl.getTodayRoutinesByType(type)
    }
}
