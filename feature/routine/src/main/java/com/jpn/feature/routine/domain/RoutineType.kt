package com.jpn.feature.routine.domain

enum class RoutineType(val id: Int) {
    HEALTH(1),
    STUDY(2),
    PERSONAL(3);
}

enum class RoutineSubType(val id: Int, val parent: RoutineType) {
    WATER(101, RoutineType.HEALTH),
    EXERCISE(102, RoutineType.HEALTH),
    PROGRAMMING(201, RoutineType.STUDY),
    THEORY(202, RoutineType.STUDY),
    KOTLIN(203, RoutineType.STUDY),
    IOS(204, RoutineType.STUDY);
}
