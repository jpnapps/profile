package com.jpn.feature.vault.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VaultHomeScreenViewModel @Inject constructor(
) : ViewModel() {

    private val _uiMessage = MutableSharedFlow<String>()
    val uiMessage = _uiMessage.asSharedFlow()

    fun onPhotoClicked() = viewModelScope.launch {
        _uiMessage.emit("Photo clicked")
    }

    fun onVideoClicked() = viewModelScope.launch {
        _uiMessage.emit("Video clicked")
    }
}