package com.jpn.core.ui.navigation

sealed class CommonNavScreen(val route: String) {
    object VaultHome : CommonNavScreen("vault/home")
}