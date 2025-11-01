package com.jpn.feature.routine.data.repository

import com.google.gson.Gson
import com.jpn.core.local.dao.RoutineDao
import com.jpn.core.utils.managers.LogManager
import com.jpn.domain.model.Routine
import com.jpn.domain.model.RoutineType
import com.jpn.domain.repository.RoutineRepository
import com.jpn.feature.routine.data.mapper.toDomain
import com.jpn.feature.routine.data.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Date
import javax.inject.Inject

class RoutineRepositoryImpl @Inject constructor(
    private val dao: RoutineDao,
    private val logManager: LogManager,
    private val gson: Gson
) : RoutineRepository {

    override fun getRoutines(): Flow<List<Routine>> {
        return dao.getAllRoutines().map { list -> list.map { it.toDomain() } }
    }

    override fun getTodayRoutinesByType(type: RoutineType): Flow<List<Routine>> {
        return dao.getRoutinesByType(type.id, Date().time)
            .map { list -> list.map { it.toDomain() } }
    }

    override suspend fun addRoutine(item: Routine) {
        logManager.d("RouRepo item ", item)
        val entitiy = item.toEntity()
        dao.insertRoutine(entitiy)
        logManager.d("RouRepo add entitiy ", entitiy)
    }

    override fun getRoutineById(id: Int): Flow<Routine> {
        return dao.getRoutineById(id).map { it.toDomain() }
    }

    override suspend fun updateRoutine(item: Routine) {
        logManager.d("RouRepo updateRoutine ", item)
        val entitiy = item.toEntity()
        dao.updateRoutine(entitiy)
        logManager.d("RouRepo update entitiy ", entitiy)
    }
}