package com.jpn.jithish.presentation.screen

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        Screen.Favorites,
        Screen.Notes,
        Screen.Profile,
        Screen.Routine,
        Screen.More
    )

    NavigationBar {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon!!, contentDescription = item.label) },
                label = { Text(item.label) },
                //selected = navBackStackEntry.value?.destination?.route == item.route,
                selected = navBackStackEntry.value?.destination?.hierarchy?.any {
                    it.route == item.route
                } == true,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(Screen.Profile.route) { inclusive = false }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}
