package com.jpn.core.ui.events

sealed class UiEvent {
    data class Toast(val message: String) : UiEvent()
    data class Error(val message: String) : UiEvent()
    data class Navigate(val route: String) : UiEvent()
    object Idle : UiEvent()
    object Loading : UiEvent()
    object Success : UiEvent()
    object NoNetwork : UiEvent()
    object RequestDeletePermission : UiEvent()
}