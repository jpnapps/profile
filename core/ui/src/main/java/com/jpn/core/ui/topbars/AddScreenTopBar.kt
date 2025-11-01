package com.jpn.core.ui.topbars

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.jpn.core.ui.R

@Composable
fun AddScreenTopBar(title: String, onBackClick: () -> Unit, onSaveClick: () -> Unit) {
    AppTopBar(
        title = title,
        onBackClick = onBackClick,
        actions = {
            IconButton(onClick = onSaveClick) {
                Icon(Icons.Default.Done, contentDescription = stringResource(R.string.save))
            }
        }
    )
}

@Composable
fun AddScreenTopBar(
    title: String,
    contentDescription: String,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit
) {
    AppTopBar(
        title = title,
        onBackClick = onBackClick,
        actions = {
            IconButton(onClick = onSaveClick) {
                Icon(Icons.Default.Done, contentDescription = contentDescription)
            }
        }
    )
}

@Composable
fun AddScreenTopBar(
    title: String,
    isSaveEnabled: Boolean,
    isAddFieldEnabled: Boolean,
    isAddSecureEnabled: Boolean,
    contentDescription: String,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    onAddField: () -> Unit,
    onAddSecure: () -> Unit,
) {
    AppTopBar(
        title = title,
        onBackClick = onBackClick,
        actions = {
            IconButton(onClick = onAddField, enabled = isAddFieldEnabled) {
                Icon(Icons.Default.Add, contentDescription = contentDescription, tint = if (isAddFieldEnabled) {
                    Color.Blue
                } else {
                    Color.Blue.copy(alpha = 0.3f)
                })
            }
            IconButton(onClick = onAddSecure , enabled = isAddSecureEnabled) {
                Icon(Icons.Default.Add, contentDescription = contentDescription, tint = if (isAddSecureEnabled) {
                    Color.Red
                } else {
                    Color.Red.copy(alpha = 0.3f)
                })
            }
            IconButton(onClick = onSaveClick , enabled = isSaveEnabled) {
                Icon(Icons.Default.Save, contentDescription = contentDescription, tint = if (isAddSecureEnabled) {
                    Color.Green
                } else {
                    Color.Green.copy(alpha = 0.3f)
                })
            }
        }
    )
}
