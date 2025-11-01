package com.jpn.feature.profile.presentation.screen.profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.jpn.core.ui.cards.SettingCard
import com.jpn.core.ui.containers.LoadingScreen
import com.jpn.core.ui.dialogs.ErrorDialog
import com.jpn.core.ui.dialogs.NoNetworkDialog
import com.jpn.core.ui.events.UiEvent
import com.jpn.core.ui.states.UiState
import com.jpn.feature.more.presentation.model.MoreMenuItemUI
import com.jpn.feature.profile.presentation.viewmodel.MoreViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MoreScreen(
    viewModel: MoreViewModel = hiltViewModel(),
    onMenuItemClick: (MoreMenuItemUI) -> Unit
) {
    //val form = viewModel.formState
    val lifecycleOwner = LocalLifecycleOwner.current
    var showDialog by remember { mutableStateOf(false) }
    var showNoNetworkDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var toastMessage by remember { mutableStateOf<String?>(null) }
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getMoreDetails()
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.uiEvent.collect { event ->
                event.getContentIfNotHandled()?.let { uiEvent ->
                    when (uiEvent) {
                        is UiEvent.Toast -> {
                            toastMessage = uiEvent.message
                        }

                        is UiEvent.NoNetwork -> {
                            showNoNetworkDialog = true
                        }

                        else -> {

                        }
                    }
                }
            }
        }
    }

    if (showNoNetworkDialog) {
        NoNetworkDialog(onRetry = {}) { }
    }

    errorMessage?.let {
        ErrorDialog(it) {}
    }


    Scaffold(
        topBar = {
            /*  SmallTopAppBar(
                  title = { Text("More", fontWeight = FontWeight.Bold) }
              )*/
        }
    ) { padding ->

        when (uiState) {
            is UiState.Loading -> {
                LoadingScreen("Fetching More Screen...")
            }

            is UiState.Success -> {
                val menuItems = (uiState as UiState.Success<List<MoreMenuItemUI>>).data
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Adaptive(minSize = 140.dp),
                    contentPadding = PaddingValues(16.dp),
                    verticalItemSpacing = 16.dp,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(padding)
                ) {
                    items(menuItems) { item ->
                        SettingCard(
                            title = item.title,
                            backgroundColor = item.backgroundColor,
                            icon = item.icon
                        ) {
                            println("Clicked: ${item.title}")
                            onMenuItemClick(item)
                        }
                    }
                }
            }

            is UiState.Error -> {
                Text(text = (uiState as UiState.Error).message)
            }
        }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Settings Preview"
)
@Composable
fun PreviewSettingsScreen() {
    /*val settingsList = listOf(
        SettingsItem("Profile", Icons.Default.Person, Color(0xFF4CAF50)),
        SettingsItem("Notifications", Icons.Default.Notifications, Color(0xFFFF9800)),
        SettingsItem("Security", Icons.Default.Lock, Color(0xFF2196F3)),
        SettingsItem("Help", Icons.Default.Settings, Color(0xFF9C27B0)),
        SettingsItem("About", Icons.Default.Info, Color(0xFF607D8B)),
        SettingsItem("Logout", Icons.Default.ExitToApp, Color(0xFFE91E63))
    )
    SettingsScreen(settingsList)*/
}
