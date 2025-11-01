package com.jpn.jithish.presentation.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jpn.feature.notes.presentation.navigation.NoteScreen
import com.jpn.jithish.presentation.navigation.appNavGraph

@Composable
fun MainScreen(navController: NavHostController = rememberNavController()) {

    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    val destination = navBackStackEntry?.destination
    val showBottomBar = destination?.hierarchy?.any {
        it.route in listOf(
            NoteScreen.List.route, // "note_list"
            Screen.Routine.route,
            Screen.Favorites.route,
            Screen.Profile.route,
            Screen.More.route
            // PwdScreen.List.route,  // "pwd_list"
        )
    } == true
    Scaffold(
        bottomBar = {
            if (showBottomBar)
                BottomNavigationBar(navController)
        }) {
        NavHost(navController, startDestination = "profile", Modifier.padding(it)) {
            appNavGraph(navController)
        }
    }
}

