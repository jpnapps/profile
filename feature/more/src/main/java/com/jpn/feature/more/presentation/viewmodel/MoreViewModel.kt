package com.jpn.feature.profile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jpn.core.ui.events.Event
import com.jpn.core.ui.events.UiEvent
import com.jpn.core.ui.states.UiState
import com.jpn.domain.usecase.GetMoreMenuItemsUseCase
import com.jpn.feature.more.presentation.mapper.toUiModel
import com.jpn.feature.more.presentation.model.MoreMenuItemUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoreViewModel @Inject constructor(
    private val getMoreMenuItemsUseCase: GetMoreMenuItemsUseCase,
) : ViewModel() {


    private val _uiState = MutableStateFlow<UiState<List<MoreMenuItemUI>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<MoreMenuItemUI>>> = _uiState

    private val _uiEvent = MutableSharedFlow<Event<UiEvent>>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        getMoreDetails()
    }

    fun getMoreDetails() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            runCatching {
                getMoreMenuItemsUseCase().map { it.toUiModel() }
            }.fold(
                onSuccess = { _uiState.value = UiState.Success(it) },
                onFailure = {
                    _uiState.value = UiState.Error("Failed: ${it.message}")
                    //_uiEvent.emit(Event(MoreMenuUiEvent.MenuClicked(-1, "Error"))) // feature-specific example
                }
            )
        }
    }
}