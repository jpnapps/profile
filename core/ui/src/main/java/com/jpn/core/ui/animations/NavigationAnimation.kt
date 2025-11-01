package com.jpn.core.ui.animations

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween

fun AnimatedContentTransitionScope<*>.enterEndToStart(): EnterTransition {
    return slideIntoContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Start,
        animationSpec = tween(500)
    )
}

fun AnimatedContentTransitionScope<*>.exitStartToEnd(): ExitTransition {
    return slideOutOfContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.End,
        animationSpec = tween(500)
    )
}