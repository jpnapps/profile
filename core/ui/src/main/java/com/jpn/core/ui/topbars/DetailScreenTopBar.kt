package com.jpn.core.ui.topbars

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable


@Composable
fun DetailScreenTopBar(title: String, contentDescription:String, onBackClick: () -> Unit, onEditClick: () -> Unit) {
    AppTopBar(
        title = title,
        onBackClick = onBackClick,
        actions = {
            IconButton(onClick = onEditClick) {
                Icon(Icons.Default.Edit, contentDescription = contentDescription)
            }
        }
    )
}
