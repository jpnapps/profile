package com.jpn.feature.study.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.jpn.core.ui.navigation.CommonNavScreen
import com.jpn.feature.study.presentation.list.EditTextScreen
import com.jpn.feature.study.presentation.list.StudyNotesScreen

fun NavGraphBuilder.studyNotesNavGraph(navController: NavHostController) {
    composable(StudyNavScreen.List.route) {
        StudyNotesScreen{
            navController.navigate(StudyNavScreen.Add.route)
        }
    }
    composable(StudyNavScreen.Add.route) {
        EditTextScreen (onSave = { navController.navigate(CommonNavScreen.VaultHome.route)}){
            navController.navigateUp()
        }
    }
}