package com.jpn.feature.vault.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.jpn.feature.vault.presentation.list.VaultHomeScreen

fun NavGraphBuilder.vaultNavGraph(navController: NavHostController) {
    composable(VaultNavScreen.Home.route) {
        VaultHomeScreen()
    }
}