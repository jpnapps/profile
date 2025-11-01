package com.jpn.core.ui.containers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jpn.core.ui.extensions.screenDefaults

@Composable
fun ScreenContainer(
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column( modifier= modifier.screenDefaults(innerPadding)
       /* modifier = modifier
            .fillMaxSize()
            .scaffoldPadding(innerPadding)
            .background(Color.White)
            .padding(Dimens.screenPaddingHorizontal, Dimens.screenPaddingVertical)*/
    ) {
        content()
    }
}