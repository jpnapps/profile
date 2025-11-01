package com.jpn.feature.routine.presentation.navigation

sealed class RoutineNavScreen(val route: String) {
    object Dash : RoutineNavScreen("routine/dash")
    object Add : RoutineNavScreen("routine/add")
    object Edit : RoutineNavScreen("routine/edit/{itemId}") {
        fun createRoute(itemId: Int) = "routine/edit/$itemId"
        const val ARG_ITEM_ID = "itemId"
    }

    object Detail : RoutineNavScreen("routine/detail/{itemId}") {
        fun createRoute(itemId: Int) = "routine/detail/$itemId"
        const val ARG_ITEM_ID = "itemId"
    }
}