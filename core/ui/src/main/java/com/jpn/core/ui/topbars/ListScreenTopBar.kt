package com.jpn.core.ui.topbars

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jpn.core.designsystem.theme.res.values.Dimens


@Composable
fun ListScreenTopBar(
    title: String,
    isExportEnabled: Boolean,
    contentDescription: String,
    onImport: () -> Unit,
    onExport: () -> Unit,
    onAdd: () -> Unit,
) {
    AppTopBarNoBack(
        title = title,
        actions = {
            IconButton(onClick = onImport) {
                Icon(
                    Icons.Filled.Download,
                    contentDescription = contentDescription,
                    tint = Color.Blue
                )
            }
            IconButton(onClick = onExport, enabled = isExportEnabled) {
                Icon(
                    Icons.Default.Upload,
                    contentDescription = contentDescription,
                    tint = if (isExportEnabled) {
                        Color.Yellow
                    } else {
                        Color.Yellow.copy(alpha = 0.3f)
                    }
                )
            }
            IconButton(onClick = onAdd) {
                Icon(Icons.Default.Add, contentDescription = contentDescription, tint = Color.Green)
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBarNoBack(
    title: String,
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        },
        navigationIcon = {
        },
        actions = actions,
        modifier = modifier,
        windowInsets = WindowInsets(Dimens.zeroDp),
        scrollBehavior = null
    )
}
