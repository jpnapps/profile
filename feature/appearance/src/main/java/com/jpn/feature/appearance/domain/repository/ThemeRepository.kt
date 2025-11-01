package com.jpn.feature.appearance.domain.repository

import com.jpn.core.designsystem.theme.AppThemeType
import kotlinx.coroutines.flow.Flow

interface AppearanceRepository {
    fun getSelectedTheme(): Flow<AppThemeType>
    suspend fun saveTheme(theme: AppThemeType)
}
