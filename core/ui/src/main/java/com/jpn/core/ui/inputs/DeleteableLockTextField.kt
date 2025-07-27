package com.jpn.core.ui.inputs

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jpn.core.ui.R

@Composable
fun DeleteableLockTextField(
    modifier: Modifier = Modifier,
    label: String,
    onLabelChange: (String) -> Unit,
    labelError: String?,
    contentValue: String,
    onContentValueChange: (String) -> Unit,
    contentLabel: String,
    contentError: String?,
    contentHint: String,
    isLocked: Boolean = false,
    borderColor: Color,
    onLockToggle: () -> Unit,
    onDeleteClicked: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.border(1.dp, borderColor)
        .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = label,
                onValueChange = onLabelChange, modifier = Modifier.weight(1f),
                isError = labelError != null,
                supportingText = {
                    labelError?.let { Text(it, color = MaterialTheme.colorScheme.error) }
                },
            )
            Spacer(modifier = Modifier.width(12.dp))
            IconButton(onClick = onLockToggle) {
                Icon(
                    imageVector = if (isLocked) Icons.Default.Lock else Icons.Default.LockOpen,
                    contentDescription = if (isLocked) stringResource(R.string.input_locked) else stringResource(
                        R.string.input_unlocked
                    )
                )
            }
            IconButton(onClick = onDeleteClicked) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = stringResource(R.string.delete)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = contentValue,
            onValueChange = onContentValueChange,
            label = { Text(contentLabel) },
            placeholder = {    // Show placeholder if the value is empty
                if (contentValue.isEmpty()) Text(contentHint)
            },
            isError = contentError != null,
            supportingText = {
                contentError?.let { Text(it, color = MaterialTheme.colorScheme.error) }
            },
            modifier = Modifier.fillMaxWidth()
        )

    }
}
