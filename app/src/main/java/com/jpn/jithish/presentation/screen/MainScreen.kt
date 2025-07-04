package com.jpn.jithish.presentation.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.jpn.jithish.presentation.navigation.appNavGraph

@Composable
fun MainScreen(navController: NavHostController = rememberNavController()) {

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }) {
        NavHost(navController, startDestination = "profile", Modifier.padding(it)) {
            appNavGraph(navController)
        }
    }
}

