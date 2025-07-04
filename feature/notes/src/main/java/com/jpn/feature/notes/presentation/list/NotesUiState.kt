package com.jpn.feature.notes.presentation.list

sealed class NotesUiState {
    object Idle : NotesUiState()
    object Loading : NotesUiState()
    object Success : NotesUiState()
    //data class Success(val notes: List<NoteUiModel>) : NotesUiState()
    data class Error(val message: String) : NotesUiState()
}
