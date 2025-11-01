package com.jpn.feature.more.presentation.navigation

sealed class MoreNavScreen(val route: String) {
    object List : MoreNavScreen("more/list")
}