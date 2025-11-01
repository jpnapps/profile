package com.jpn.feature.vault.presentation.navigation

sealed class VaultNavScreen(val route: String) {
    object Home : VaultNavScreen("vault/home")
}