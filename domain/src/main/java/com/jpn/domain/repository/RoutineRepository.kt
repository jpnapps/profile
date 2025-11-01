package com.jpn.domain.repository

import com.jpn.domain.model.Routine
import com.jpn.domain.model.RoutineType
import kotlinx.coroutines.flow.Flow

interface RoutineRepository {
    fun getRoutines(): Flow<List<Routine>>
    fun getTodayRoutinesByType(type: RoutineType): Flow<List<Routine>>
    suspend fun addRoutine(item: Routine)
    fun getRoutineById(id:Int): Flow<Routine>
    suspend fun updateRoutine(item: Routine)
}