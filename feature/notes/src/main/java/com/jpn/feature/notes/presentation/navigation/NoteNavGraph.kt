package com.jpn.feature.notes.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.jpn.feature.notes.presentation.add.AddNoteScreen
import com.jpn.feature.notes.presentation.list.NotesScreen

fun NavGraphBuilder.noteNavGraph(navController: NavHostController) {
    composable(NoteScreen.Add.route) {
        AddNoteScreen { navController.popBackStack() }
    }
    composable(NoteScreen.List.route) {
        NotesScreen { navController.navigate(NoteScreen.Add.route) }
    }
}
