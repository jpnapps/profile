package com.jpn.core.ui.extensions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.jpn.core.designsystem.theme.res.values.Dimens


fun Modifier.screenDefaults(innerPadding: PaddingValues) = this
    .fillMaxSize()
    .background(Color.White)
    .padding(innerPadding) // still applies padding
    .consumeWindowInsets(innerPadding)
    .padding(horizontal = Dimens.screenPaddingHorizontal, vertical = Dimens.screenPaddingVertical)