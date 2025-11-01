package com.jpn.feature.more.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

fun NavGraphBuilder.moreNavGraph(navController: NavHostController) {
    composable(MoreNavScreen.List.route) {
    }
}
