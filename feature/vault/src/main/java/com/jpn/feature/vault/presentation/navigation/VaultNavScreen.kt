package com.jpn.feature.vault.presentation.navigation

sealed class VaultNavScreen(val route: String) {
    object Home : VaultNavScreen("vault/home")
    object Photo : VaultNavScreen("vault/photo")
    object Video : VaultNavScreen("vault/video")
    object Gallery : VaultNavScreen("vault/gallery")
}