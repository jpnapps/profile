package com.jpn.feature.pwd.presentation.navigation

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jpn.feature.pwd.presentation.add.AddPwdScreen
import com.jpn.feature.pwd.presentation.detail.PwdDetailScreen
import com.jpn.feature.pwd.presentation.edit.EditPwdScreen
import com.jpn.feature.pwd.presentation.list.PwdsScreen

fun NavGraphBuilder.pwdNavGraph(navController: NavHostController) {
    composable(PwdScreen.Add.route) {
        AddPwdScreen { navController.popBackStack() }
    }
/*    composable(PwdScreen.Edit.route) {
        val itemId = navController.previousBackStackEntry
            ?.savedStateHandle
            ?.get<Int>(NavArgs.ITEM_ID) ?: Digits.ZERO
        Log.d("jpLogs", "pwdNavGraph Edit.route itemId: " + itemId)
        EditPwdScreen(id = itemId) { navController.popBackStack() }
    }*/
    composable(PwdScreen.List.route) {
        PwdsScreen(onItemClick = { id ->
            navController.currentBackStackEntry?.savedStateHandle?.set(NavArgs.ITEM_ID, id)
            Log.d("jpLogs", "pwdNavGraph List.route id " + id)
           // navController.navigate(PwdScreen.Detail.route)
            navController.navigate(PwdScreen.Detail.createRoute(id))
        }) { navController.navigate(PwdScreen.Add.route) }
    }
/*    composable(PwdScreen.Detail.route) {
        val itemId = navController.previousBackStackEntry
            ?.savedStateHandle
            ?.get<Int>(NavArgs.ITEM_ID) ?: Digits.ZERO
        Log.d("jpLogs", "pwdNavGraph Detail.route itemId: " + itemId)
        PwdDetailScreen(id = itemId, onBackClick = {
            Log.d("jpLogs", "pwdNavGraph Detail.route  onBackClick ")
            navController.popBackStack()
        }) {
            Log.d("jpLogs", "pwdNavGraph Detail.route  onEditClick ")
            navController.currentBackStackEntry?.savedStateHandle?.set(NavArgs.ITEM_ID, itemId)
            navController.navigate(PwdScreen.Edit.route)
        }
    }*/

    composable(
        route = PwdScreen.Detail.route,
        arguments = listOf(navArgument(PwdScreen.Detail.ARG_ITEM_ID) {
            type = NavType.IntType
        })
    ) { backStackEntry ->
        val itemId = backStackEntry.arguments?.getInt(PwdScreen.Detail.ARG_ITEM_ID) ?: 0
        Log.d("jpLogs", "pwdNavGraph Detail.route itemId: " + itemId)
        PwdDetailScreen(
            id = itemId,
            onBackClick = { navController.popBackStack() },
            onEditClick = {
                navController.navigate(PwdScreen.Edit.createRoute(itemId))
            }
        )
    }

    composable(
        route = PwdScreen.Edit.route,
        arguments = listOf(navArgument(PwdScreen.Edit.ARG_ITEM_ID) {
            type = NavType.IntType
        })
    ) { backStackEntry ->
        val itemId = backStackEntry.arguments?.getInt(PwdScreen.Edit.ARG_ITEM_ID) ?: 0
        Log.d("jpLogs", "pwdNavGraph Edit.route itemId: " + itemId)
        EditPwdScreen(
            id = itemId,
            onBackClick = { navController.popBackStack() }
        )
    }




}
