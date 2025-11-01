package com.jpn.feature.study.presentation.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import com.jpn.core.ui.box.BoxGreyBorder
import com.jpn.core.ui.containers.LoadingScreen
import com.jpn.core.ui.states.UiState
import com.jpn.feature.study.presentation.model.StudyNoteUiModel
import com.jpn.feature.study.presentation.viewmodel.StudyNotesScreenViewModel

@Composable
fun StudyNotesScreen(
    viewModel: StudyNotesScreenViewModel = hiltViewModel(),
    onAddClick: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onAddClick()
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add Note")
            }
        }) { padding ->
        StudyNoteContent(viewModel, Modifier.padding(padding))
    }
}

@Composable
fun StudyNoteContent(viewModel: StudyNotesScreenViewModel, modifier: Modifier) {
    val state by viewModel.uiState.collectAsState()
    when (state) {
        is UiState.Loading -> LoadingScreen()
        is UiState.Error -> Text("⚠ ${(state as UiState.Error).message}")
        is UiState.Success -> {
            val routines = (state as UiState.Success<List<StudyNoteUiModel>>).data
            LazyColumn(modifier = modifier) {
                items(routines.size) { index ->
                    val note = routines.get(index)
                    BoxGreyBorder(onClick = {

                    }){
                    Column {
                        Text(
                            text = note.title,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = note.desc,
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
                }
            }
        }
    }
}