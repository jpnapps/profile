package com.jpn.jithish.presentation.screen

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavController) {
   /* val items = listOf(
        NavItem("Favorites", "favorites", Icons.Default.Favorite),
        NavItem("Notes", "notes", Icons.Default.Edit),
        NavItem("Profile", "profile", Icons.Default.Person),
        NavItem("Study", "study", Icons.Default.Settings),
        NavItem("Settings", "settings", Icons.Default.MoreVert)
    )*/
    val items = listOf(
        Screen.Favorites,
        Screen.Notes,
        Screen.Profile,
        Screen.Pwd,
        Screen.Settings
    )
    NavigationBar {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon!!, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = navBackStackEntry.value?.destination?.route == item.route,
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

//data class NavItem(val title: String, val route: String, val icon: ImageVector)
//data class NavItem(val screen: Screen)
