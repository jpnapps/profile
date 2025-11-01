package com.jpn.feature.more.presentation.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.jpn.core.designsystem.theme.Purple40

sealed class MoreMenuItemUI(val title: String, val backgroundColor: Color, val icon: ImageVector?) {
    data object Pwd : MoreMenuItemUI("Password", Purple40, Icons.Default.Lock)
    data object Profile : MoreMenuItemUI("Profile", Color.Red, Icons.Default.Person)
    data object Settings : MoreMenuItemUI("Settings", Color.Green, Icons.Default.Settings)
    data object Help : MoreMenuItemUI("Help", Color.Magenta, Icons.Default.Info)
    data object Study : MoreMenuItemUI("Study", Color.Magenta, Icons.Default.Info)
    data object Vault : MoreMenuItemUI("Gallery", Color.Blue, Icons.Default.Lock)
    data object Theme : MoreMenuItemUI("Theme", Color.Gray, Icons.Default.Edit)
    data object Logout : MoreMenuItemUI("Logout", Color.Yellow, Icons.Default.ExitToApp)
}