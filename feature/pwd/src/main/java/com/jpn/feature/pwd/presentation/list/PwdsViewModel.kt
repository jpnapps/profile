package com.jpn.feature.notes.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jpn.domain.profile.model.PwdData
import com.jpn.domain.profile.usecase.AddPwdUseCase
import com.jpn.domain.profile.usecase.GetPwdsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class PwdsViewModel @Inject constructor(
    private val getPwdsUseCase: GetPwdsUseCase,
    private val addPwdUseCase: AddPwdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<PwdsUiState>(PwdsUiState.Idle)
    val uiState: StateFlow<PwdsUiState> = _uiState

    val pwds: StateFlow<List<PwdData>> = getPwdsUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}
