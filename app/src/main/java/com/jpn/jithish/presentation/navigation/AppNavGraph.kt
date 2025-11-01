package com.jpn.jithish.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.jpn.feature.appearance.presentation.navigation.AppearanceNavScreen
import com.jpn.feature.appearance.presentation.navigation.appearanceNavGraph
import com.jpn.feature.favorites.presentation.screen.list.FavoriteScreen
import com.jpn.feature.more.presentation.model.MoreMenuItemUI
import com.jpn.feature.notes.presentation.navigation.NoteScreen
import com.jpn.feature.notes.presentation.navigation.noteNavGraph
import com.jpn.feature.profile.presentation.screen.ProfileScreen
import com.jpn.feature.profile.presentation.screen.profile.MoreScreen
import com.jpn.feature.pwd.presentation.navigation.PwdScreen
import com.jpn.feature.pwd.presentation.navigation.pwdNavGraph
import com.jpn.feature.routine.presentation.navigation.RoutineNavScreen
import com.jpn.feature.routine.presentation.navigation.routineNavGraph
import com.jpn.feature.study.presentation.navigation.StudyNavScreen
import com.jpn.feature.study.presentation.navigation.studyNotesNavGraph
import com.jpn.feature.vault.presentation.navigation.VaultNavScreen
import com.jpn.feature.vault.presentation.navigation.vaultNavGraph
import com.jpn.jithish.presentation.screen.Screen

fun NavGraphBuilder.appNavGraph(navController: NavHostController) {
    navigation(
        startDestination = NoteScreen.List.route, route = Screen.Notes.route
    ) {
        noteNavGraph(navController)
    }
    navigation(
        startDestination = PwdScreen.List.route, route = Screen.Pwd.route
    ) {
        pwdNavGraph(navController)
    }
    navigation(
        startDestination = RoutineNavScreen.Dash.route, route = Screen.Routine.route
    ) {
        routineNavGraph(navController)
    }
    navigation(
        route = Screen.StudyNotes.route,
        startDestination = StudyNavScreen.List.route
    ) {
        studyNotesNavGraph(navController)
    }
    navigation(
        route = Screen.Vault.route,
        startDestination = VaultNavScreen.Home.route
    ) {
        vaultNavGraph(navController)
    }
    navigation(
        route = Screen.Theme.route,
        startDestination = AppearanceNavScreen.Home.route
    ) {
        appearanceNavGraph(navController)
    }
    //composable(Screen.Routine.route) { RoutineScreen()  }
    // composable(Screen.StudyNotes.route) { StudyNotesScreen() }
    composable(Screen.Profile.route) { ProfileScreen()
        //openProfileActivity(context = )
    }
    composable(Screen.Favorites.route) { FavoriteScreen() }
    composable(Screen.More.route) {
        MoreScreen(
            onMenuItemClick = { menuItem ->
                when (menuItem) {
                    MoreMenuItemUI.Pwd -> navController.navigate(Screen.Pwd.route)
                    MoreMenuItemUI.Study -> navController.navigate(Screen.StudyNotes.route)
                    MoreMenuItemUI.Theme -> navController.navigate(Screen.Theme.route)
                    //MoreMenuItemUI.Vault -> navController.navigate(Screen.Vault.route)
                    else -> {}
                }
            })
    }
}