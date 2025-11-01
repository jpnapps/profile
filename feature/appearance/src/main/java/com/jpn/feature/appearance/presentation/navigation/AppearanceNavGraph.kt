package com.jpn.feature.appearance.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.jpn.feature.appearance.presentation.home.ThemeSelectionScreen

fun NavGraphBuilder.appearanceNavGraph(navController: NavHostController) {
    composable(AppearanceNavScreen.Home.route) {
        ThemeSelectionScreen { navController.navigateUp() }
    }
}