package com.jpn.feature.routine.presentation.model

import com.jpn.domain.model.RoutineSubType
import com.jpn.domain.model.RoutineType

data class RoutineUiModel(
    val id: Long = 0L,
    val name: String,
    val type: RoutineType,           // Health, Study, Program, etc.
    val subType: RoutineSubType?,    // Water, Exercise, Reading, etc.
    var progress: Int,
    var step: Int,
    var goal: Int,
    val date: Long,                  // The day this routine is logged
    val updatedAt:Long
)
