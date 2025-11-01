package com.jpn.feature.appearance.presentation.navigation

sealed class AppearanceNavScreen(val route: String) {
    object Home : AppearanceNavScreen("appearance/home")
}