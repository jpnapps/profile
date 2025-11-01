package com.jpn.core.designsystem.domain

import kotlinx.coroutines.flow.Flow

interface ThemeDataStoreManager {
    val selectedTheme: Flow<String>
    suspend fun saveTheme(theme: String)
}