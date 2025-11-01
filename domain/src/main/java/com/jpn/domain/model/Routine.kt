package com.jpn.domain.model

data class Routine(
    val id: Long = 0L,
    val name: String,
    val type: RoutineType,           // Health, Study, Program, etc.
    val subType: RoutineSubType?,    // Water, Exercise, Reading, etc.
    var step: Int,
    var progress: Int,
    var goal: Int,
    val date: Long,                  // The day this routine is logged
    val updatedAt: Long
)

/**
 * Top-level routine categories.
 */
enum class RoutineType(val id: Int) {
    HEALTH(1),
    STUDY(2),
    PERSONAL(3)
}

/**
 * Sub-routines belonging to a type.
 */
enum class RoutineSubType(val id: Int, val parentType: RoutineType) {
    WATER(100, RoutineType.HEALTH),
    EXERCISE(101, RoutineType.HEALTH),
    THEORY(200, RoutineType.STUDY),
    PROGRAM(201, RoutineType.STUDY),
    PROJECT(202, RoutineType.STUDY),
    MEDITATION(300, RoutineType.PERSONAL),
    CUSTOM(301, RoutineType.PERSONAL)
}