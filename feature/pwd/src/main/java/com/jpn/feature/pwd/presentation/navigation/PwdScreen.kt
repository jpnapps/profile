package com.jpn.feature.notes.presentation.navigation

sealed class PwdScreen(val route: String) {
    object List : PwdScreen("pwd/list")
    object Add : PwdScreen("pwd/add")
    object Detail : PwdScreen("pwd/detail")
}