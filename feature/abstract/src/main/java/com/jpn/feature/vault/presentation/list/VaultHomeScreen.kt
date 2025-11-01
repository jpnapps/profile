package com.jpn.feature.vault.presentation.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.jpn.core.ui.cards.SettingCard
import com.jpn.core.ui.theme.StudyLight
import com.jpn.feature.study.presentation.viewmodel.VaultHomeScreenViewModel

@Composable
fun VaultHomeScreen(viewModel: VaultHomeScreenViewModel = hiltViewModel()) {

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(Icons.Default.Add, contentDescription = "Add Note")
            }
        }) { padding ->
        SettingCard(title = "Photo", backgroundColor = StudyLight, icon = Icons.Default.Lock , modifier = Modifier.padding(padding)){
            println("Clicked: Photo")

        }
    }
}

