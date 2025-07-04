package com.jpn.feature.pwds.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jpn.feature.notes.presentation.list.PwdsViewModel

@Composable
fun PwdsScreen(viewModel: PwdsViewModel = hiltViewModel(),  onItemClick: (Int) -> Unit, onAddClick: () -> Unit) {
    val pwds by viewModel.pwds.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(Icons.Default.Add, contentDescription = "Add Password")
            }
        }) { padding ->

        LazyColumn(modifier = Modifier.padding(padding)) {
            items(pwds.size) { index ->
                val item = pwds.get(index)
                Text(
                    text = item.key,
                    modifier = Modifier.padding(16.dp).clickable { onItemClick(item.id) },
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}