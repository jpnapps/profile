package com.jpn.feature.pwd.presentation.list

sealed class PwdsUiState {
    object Idle : PwdsUiState()
    object Loading : PwdsUiState()
    object Success : PwdsUiState()
    data class Error(val message: String) : PwdsUiState()
}
