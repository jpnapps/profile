package com.jpn.jithish.presentation.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jpn.feature.favorites.presentation.screen.list.FavoriteScreen
import com.jpn.feature.favorites.presentation.viewmodel.FavoriteViewModel
import com.jpn.feature.profile.presentation.screen.profile.ProfileScreen


@Composable
fun MainScreen(navController: NavHostController = rememberNavController()) {
    //val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) {
        NavHost(navController, startDestination = "profile", Modifier.padding(it)) {
            composable("favorites") {
                val viewModel: FavoriteViewModel = hiltViewModel()
                FavoriteScreen(viewModel)
            }
            composable("notes") { /* TODO */ }
            composable("profile") {
               // ProfileScreen()
            }
            composable("study") { /* TODO */ }
            composable("settings") { /* TODO */ }
        }
    }
}