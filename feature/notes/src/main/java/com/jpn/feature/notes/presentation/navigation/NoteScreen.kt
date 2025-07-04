package com.jpn.feature.notes.presentation.navigation

sealed class NoteScreen(val route: String) {
    object List : NoteScreen("notes/list")
    object Add : NoteScreen("notes/add")
}