package com.jpn.core.designsystem.domain

import kotlinx.coroutines.flow.Flow

interface ThemePreferenceDataSource {
    fun getSelectedTheme(): Flow<String>
    suspend fun saveTheme(theme: String)
}