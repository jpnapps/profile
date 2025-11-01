package com.jpn.feature.vault.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.jpn.feature.vault.presentation.list.VaultGalleryScreen
import com.jpn.feature.vault.presentation.list.VaultHomeScreen
import com.jpn.feature.vault.presentation.list.VaultPhotosScreen

fun NavGraphBuilder.vaultNavGraph(navController: NavHostController) {
    composable(VaultNavScreen.Home.route) {
        VaultHomeScreen(onPhotoClick = { navController.navigate(VaultNavScreen.Photo.route) }) {}
    }
    composable(VaultNavScreen.Photo.route) {
        VaultPhotosScreen(onBackClick = { navController.popBackStack() }) {
            navController.navigate(VaultNavScreen.Gallery.route)
        }
    }
    composable(VaultNavScreen.Gallery.route) {
        VaultGalleryScreen(onBackClick = { navController.popBackStack() }) {}
    }
}