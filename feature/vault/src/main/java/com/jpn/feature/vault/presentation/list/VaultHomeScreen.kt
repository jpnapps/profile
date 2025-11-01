package com.jpn.feature.vault.presentation.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jpn.core.designsystem.theme.StudyLight
import com.jpn.core.ui.cards.SettingCard
import com.jpn.core.ui.topbars.AppTopBarNoBack

@Composable
fun VaultHomeScreen(viewModel: VaultHomeScreenViewModel = hiltViewModel(),  onPhotoClick: () -> Unit,
                    onVideoClick: () -> Unit) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        viewModel.uiMessage.collect { message ->
            snackbarHostState.showSnackbar(message)
        }
    }

    Scaffold(
        topBar = {
            AppTopBarNoBack(
                title = "Vault",
                actions = {}
            )
        }, snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                SettingCard(
                    modifier = Modifier.weight(1f).aspectRatio(1f),
                    title = "Photo",
                    backgroundColor = StudyLight,
                    icon = Icons.Default.Lock
                ) {
                    viewModel.onPhotoClicked()
                    onPhotoClick()
                }
                Spacer(modifier = Modifier.height(32.dp))
                SettingCard(
                    modifier = Modifier.weight(1f).aspectRatio(1f),
                    title = "Video",
                    backgroundColor = Color.Green,
                    icon = Icons.Default.Lock
                ) {
                    viewModel.onVideoClicked()
                    onVideoClick()
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Optional future area for additional features
            // Text("More coming soon...", color = Color.Gray)
        }
    }
}

