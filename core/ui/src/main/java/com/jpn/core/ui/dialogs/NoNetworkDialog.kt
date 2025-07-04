package com.jpn.core.ui.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.jpn.core.ui.R

@Composable
fun NoNetworkDialog(
    onRetry: () -> Unit, onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onRetry) {
                Text(stringResource(R.string.dialog_retry))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.dialog_cancel))
            }
        },
        title = { Text(stringResource(R.string.dialog_no_internet)) },
        text = { Text(stringResource(R.string.dialog_check_connection)) })
}
