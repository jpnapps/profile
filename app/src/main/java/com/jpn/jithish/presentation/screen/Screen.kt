package com.jpn.jithish.presentation.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val label: String, val icon: ImageVector?) {
    object Favorites : Screen("favorites", "Favorites", Icons.Default.Favorite)
    object Notes : Screen("notes", "Notes", Icons.Default.Edit)
    object Profile : Screen("profile", "Profile", Icons.Default.Person)
    object Pwd : Screen("pwd", "Password", Icons.Default.Settings)
    object Settings : Screen("settings", "Settings", Icons.Default.MoreVert)
}