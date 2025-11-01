package com.jpn.feature.routine.data.mapper

import com.jpn.core.local.entity.RoutineEntity
import com.jpn.domain.model.Routine
import com.jpn.domain.model.RoutineSubType
import com.jpn.domain.model.RoutineType

fun RoutineEntity.toDomain(): Routine = Routine(
    id = id,
    name = name,
    type = RoutineType.values().first { it.id == type },
    subType = RoutineSubType.values().firstOrNull { it.id == subType },
    progress = progress,
    goal = goal,
    step = step,
    date = date,
    updatedAt = updatedAt
)

fun Routine.toEntity(): RoutineEntity = RoutineEntity(
    id = id,
    name = name,
    type = type.id,
    subType = subType?.id ?: 0,
    progress = progress,
    goal = goal,
    step = step,
    date = date,
    updatedAt = updatedAt
)

