package com.jpn.core.ui.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.jpn.core.ui.R

@Composable
fun SuccessDialog(
    message: String, onDismiss: () -> Unit
) {
    AlertDialog(onDismissRequest = onDismiss, confirmButton = {
        TextButton(onClick = onDismiss) {
            Text(stringResource(R.string.dialog_ok))
        }
    }, title = { Text(stringResource(R.string.dialog_success)) }, text = { Text(message) })
}
