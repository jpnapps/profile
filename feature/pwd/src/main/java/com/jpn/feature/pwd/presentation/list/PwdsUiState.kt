package com.jpn.feature.pwd.presentation.list

sealed class PwdsUiEvent {
    object Idle : PwdsUiEvent()
    object Loading : PwdsUiEvent()
    object Success : PwdsUiEvent()
    data class Error(val message: String) : PwdsUiEvent()
    object CopytoClip : PwdsUiEvent()
    data class ShowToast(val message: String) : PwdsUiEvent()
}
