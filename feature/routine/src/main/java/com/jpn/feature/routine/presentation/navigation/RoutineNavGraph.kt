package com.jpn.feature.routine.presentation.navigation

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jpn.feature.routine.presentation.dash.RoutineScreen

fun NavGraphBuilder.routineNavGraph(navController: NavHostController) {
    composable(RoutineNavScreen.Add.route) {
       // AddPwdScreen { navController.popBackStack() }
    }

    composable(RoutineNavScreen.Dash.route) {
        RoutineScreen(onItemClick = { id ->
            navController.currentBackStackEntry?.savedStateHandle?.set(NavArgs.ITEM_ID, id)
            Log.d("jpLogs", "routineNavGraph Dash.route id " + id)
            navController.navigate(RoutineNavScreen.Detail.createRoute(id))
        }) { navController.navigate(RoutineNavScreen.Add.route) }
    }

    composable(
        route = RoutineNavScreen.Detail.route,
        arguments = listOf(navArgument(RoutineNavScreen.Detail.ARG_ITEM_ID) {
            type = NavType.IntType
        })
    ) { backStackEntry ->
        val itemId = backStackEntry.arguments?.getInt(RoutineNavScreen.Detail.ARG_ITEM_ID) ?: 0
        Log.d("jpLogs", "routineNavGraph Detail.route itemId: " + itemId)
       /* PwdDetailScreen(
            id = itemId,
            onBackClick = { navController.popBackStack() },
            onEditClick = {
                navController.navigate(RoutineNavScreen.Edit.createRoute(itemId))
            }
        )*/
    }

    composable(
        route = RoutineNavScreen.Edit.route,
        arguments = listOf(navArgument(RoutineNavScreen.Edit.ARG_ITEM_ID) {
            type = NavType.IntType
        })
    ) { backStackEntry ->
        val itemId = backStackEntry.arguments?.getInt(RoutineNavScreen.Edit.ARG_ITEM_ID) ?: 0
        Log.d("jpLogs", "routineNavGraph Edit.route itemId: " + itemId)
        /*EditPwdScreen(
            id = itemId,
            onBackClick = { navController.popBackStack() }
        )*/
    }
}