package com.jpn.feature.vault.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jpn.core.ui.events.UiEvent
import com.jpn.core.utils.managers.LogManager
import com.jpn.domain.model.MediaItem
import com.jpn.domain.model.MediaType
import com.jpn.domain.repository.VaultRepository
import com.jpn.domain.usecase.vault.GetMediaPhotosUseCase
import com.jpn.domain.usecase.vault.ImportToVaultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VaultGalleryViewModel @Inject constructor(
    private val repository: VaultRepository,
    private val importToVaultUseCase: ImportToVaultUseCase,
    private val getMediaPhotosUseCase:GetMediaPhotosUseCase,
    private val logManager: LogManager
) : ViewModel() {

    private val _mediaItems = MutableStateFlow<List<MediaItem>>(emptyList())
    val mediaItems: StateFlow<List<MediaItem>> = _mediaItems

    private val _selectedItems = MutableStateFlow<Set<MediaItem>>(emptySet())
    val selectedItems: StateFlow<Set<MediaItem>> = _selectedItems

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        loadMedia()
    }

    fun loadMedia(mediaType: MediaType = MediaType.Photo) {
        viewModelScope.launch {
            val items = getMediaPhotosUseCase() // query MediaStore
            logManager.d("VGVM loadMedia items: " + items.size)
            _mediaItems.value = items
        }
    }

    fun toggleSelection(item: MediaItem) {
        val current = _selectedItems.value.toMutableSet()
        if (current.contains(item)) current.remove(item) else current.add(item)
        _selectedItems.value = current
    }

    fun addToVault() {
        viewModelScope.launch {
             importToVaultUseCase(_selectedItems.value.toList()){ intentSender ->
                 // emit to Compose via UiEvent
                 //_uiEvent.emit(UiEvent.RequestDeletePermission(intentSender))
             }
            //repository.saveToVault(_selectedItems.value.toList())
            _selectedItems.value = emptySet()
            //_uiEvent.emit()
            _uiEvent.emit(UiEvent.Success)
        }
    }
}
