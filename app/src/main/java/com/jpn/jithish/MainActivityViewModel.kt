package com.jpn.feature.appearance.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jpn.core.designsystem.theme.AppThemeType
import com.jpn.feature.appearance.domain.usecase.GetSelectedThemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getSelectedThemeUseCase: GetSelectedThemeUseCase,
) : ViewModel() {

    val selectedTheme = getSelectedThemeUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AppThemeType.SUNNY)
}