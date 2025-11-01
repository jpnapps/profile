package com.jpn.feature.vault.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jpn.core.utils.managers.LogManager
import com.jpn.domain.model.MediaItem
import com.jpn.domain.usecase.vault.GetVaultPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VaultPhotoScreenViewModel @Inject constructor(
    private val getVaultPhotosUseCase: GetVaultPhotosUseCase,
    private val logManager: LogManager
) : ViewModel() {

    private val _mediaItems = MutableStateFlow<List<MediaItem>>(emptyList())
    val mediaItems: StateFlow<List<MediaItem>> = _mediaItems

    private val _uiMessage = MutableSharedFlow<String>()
    val uiMessage = _uiMessage.asSharedFlow()

    fun loadPhotos() {
        viewModelScope.launch {
            val items = getVaultPhotosUseCase()
            logManager.d("VPSVM loadPhotos items: " + items.size)
            _mediaItems.value = items
        }
    }
}