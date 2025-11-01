package com.jpn.feature.study.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jpn.core.ui.events.Event
import com.jpn.core.ui.events.UiEvent
import com.jpn.core.ui.states.UiState
import com.jpn.core.ui.states.asResult
import com.jpn.domain.usecase.study.GetStudyNotesUseCase
import com.jpn.feature.study.presentation.mapper.toUiModel
import com.jpn.feature.study.presentation.model.StudyNoteUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudyNotesScreenViewModel @Inject constructor(
    private val getStudyNotesUseCase: GetStudyNotesUseCase
) : ViewModel() {


    private val _uiState = MutableStateFlow<UiState<List<StudyNoteUiModel>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<StudyNoteUiModel>>> = _uiState

    private val _uiEvent = MutableSharedFlow<Event<UiEvent>>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        getMoreDetails()
    }

    fun getMoreDetails() {
        viewModelScope.launch {
            getStudyNotesUseCase()
                .map {  it.map { it.toUiModel() }

                }
                .asResult()
                .collect { _uiState.value = it }
        }
        /*viewModelScope.launch {
            _uiState.value = UiState.Loading
            runCatching {
                getStudyNotesUseCase().map { it.toUiModel() }
            }.fold(
                onSuccess = { _uiState.value = UiState.Success(it) },
                onFailure = {
                    _uiState.value = UiState.Error("Failed: ${it.message}")
                }
            )
        }*/
    }
}