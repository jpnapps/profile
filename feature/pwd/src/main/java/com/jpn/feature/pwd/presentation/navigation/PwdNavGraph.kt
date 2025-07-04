package com.jpn.feature.notes.presentation.navigation

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.jpn.feature.notes.presentation.add.AddPwdScreen
import com.jpn.feature.pwd.presentation.detail.PwdDetailScreen
import com.jpn.feature.pwds.presentation.list.PwdsScreen

fun NavGraphBuilder.pwdNavGraph(navController: NavHostController) {
    composable(PwdScreen.Add.route) {
        AddPwdScreen { navController.popBackStack() }
    }
    composable(PwdScreen.List.route) {
        PwdsScreen (  onItemClick = { id ->
            navController.currentBackStackEntry?.savedStateHandle?.set("id", id)
            Log.d("jpLogs","pwdNavGraph PwdsScreen id "+id)
            navController.navigate(PwdScreen.Detail.route)
        } ) { navController.navigate(PwdScreen.Add.route) }
    }
    composable(PwdScreen.Detail.route) {
        val itemId=navController.previousBackStackEntry
            ?.savedStateHandle
            ?.get<Int>("id")?:0
        Log.d("jpLogs","pwdNavGraph PwdDetailScreen itemId: "+itemId)
        PwdDetailScreen (id=itemId) { navController.popBackStack() }
    }
}
