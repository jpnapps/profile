package com.jpn.feature.appearance.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jpn.core.designsystem.theme.AppThemeType
import com.jpn.core.ui.box.BoxGreyBorder
import com.jpn.core.ui.topbars.AppTopBar
import com.jpn.feature.appearance.presentation.viewmodel.ThemeSelectionViewModel

@Composable
fun ThemeSelectionScreen(
    viewModel: ThemeSelectionViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val selectedTheme by viewModel.selectedTheme.collectAsState()
    Scaffold(
        topBar = {
            AppTopBar(
                title = "Theme",
                onBackClick = onBackClick
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            items(AppThemeType.entries.size) { index ->
                val theme = AppThemeType.entries[index]
                BoxGreyBorder(onClick = {

                }) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                viewModel.saveTheme(theme)
                            } 
                            .padding(16.dp)
                    ) {
                        RadioButton(
                            selected = theme == selectedTheme,
                            onClick = {
                                viewModel.saveTheme(theme)
                            }
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(theme.name)
                    }
                }
            }
        }
    }
}
