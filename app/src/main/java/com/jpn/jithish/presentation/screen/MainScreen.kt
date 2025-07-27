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
import com.jpn.feature.pwd.presentation.navigation.PwdScreen
import com.jpn.jithish.presentation.navigation.appNavGraph

@Composable
fun MainScreen(navController: NavHostController = rememberNavController()) {

/*    val currentDestination = navController
        .currentBackStackEntryAsState().value?.destination?.route*/
    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    val destination = navBackStackEntry?.destination
/*    val showBottomBar = currentDestination in listOf(
        Screen.Favorites.route,
        Screen.Notes.route,
        Screen.Profile.route,
        Screen.Pwd.route,
        Screen.Settings.route
    )*/
    val showBottomBar = destination?.hierarchy?.any {
        it.route in listOf(
            NoteScreen.List.route, // "note_list"
            PwdScreen.List.route,  // "pwd_list"
            Screen.Favorites.route,
            Screen.Profile.route,
            Screen.Settings.route
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

