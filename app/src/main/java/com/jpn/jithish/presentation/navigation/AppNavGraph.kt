package com.jpn.jithish.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.jpn.feature.favorites.presentation.screen.list.FavoriteScreen
import com.jpn.feature.notes.presentation.navigation.NoteScreen
import com.jpn.feature.notes.presentation.navigation.PwdScreen
import com.jpn.feature.notes.presentation.navigation.noteNavGraph
import com.jpn.feature.notes.presentation.navigation.pwdNavGraph
import com.jpn.feature.profile.presentation.screen.profile.ProfileScreen
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
    composable(Screen.Profile.route) { ProfileScreen() }
    composable(Screen.Favorites.route) { FavoriteScreen() }
    composable(Screen.Settings.route) { }
}