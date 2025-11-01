package com.jpn.feature.appearance.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jpn.core.designsystem.theme.AppThemeType
import com.jpn.feature.appearance.domain.usecase.GetSelectedThemeUseCase
import com.jpn.feature.appearance.domain.usecase.SaveThemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeSelectionViewModel @Inject constructor(
    private val getSelectedThemeUseCase: GetSelectedThemeUseCase,
    private val saveThemeUseCase: SaveThemeUseCase
) : ViewModel() {

    val selectedTheme = getSelectedThemeUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AppThemeType.SUNNY)

    fun saveTheme(theme: AppThemeType) {
        viewModelScope.launch { saveThemeUseCase(theme) }
    }
}