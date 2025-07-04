package com.jpn.feature.notes.presentation.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OthersInput(
    refLinks: List<String>,
    onAddLink: (String) -> Unit,
    onRemoveLink: (String) -> Unit
) {
    var currentLink by remember { mutableStateOf("") }

    Column {
        OutlinedTextField(
            value = currentLink,
            onValueChange = { currentLink = it },
            label = { Text("Referral Link") }
        )

        Button(onClick = {
            if (currentLink.isNotBlank()) {
                onAddLink(currentLink.trim())
                currentLink = ""
            }
        }) {
            Text("Add Link")
        }

        Spacer(Modifier.height(8.dp))

        LazyColumn {
            items(refLinks.size) { index ->
                val link = refLinks[index]
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(link)
                    IconButton(onClick = { onRemoveLink(link) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Remove")
                    }
                }
            }
        }
    }
}