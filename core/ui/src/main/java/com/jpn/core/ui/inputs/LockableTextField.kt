package com.jpn.core.ui.inputs

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jpn.core.ui.R

@Composable
fun LockableTextField(
    value: String,
    onValueChange: (String) -> Unit,
    isLocked: Boolean =false,
    onLockToggle: () -> Unit
) {
   // var isLocked by remember { mutableStateOf(isLocked) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange, modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(12.dp))
        IconButton(onClick = onLockToggle) {
            Icon(
                imageVector = if (isLocked) Icons.Default.Lock else Icons.Default.LockOpen,
                contentDescription = if (isLocked) stringResource(R.string.input_locked) else stringResource(R.string.input_unlocked)
            )
        }
    }
}
