package com.jpn.feature.study.presentation.navigation

sealed class StudyNavScreen(val route: String) {
    object List : StudyNavScreen("study/list")
    object Add : StudyNavScreen("study/add")
}