package com.jpn.feature.appearance.data

import com.jpn.core.designsystem.domain.ThemeDataStoreManager
import com.jpn.core.designsystem.domain.ThemePreferenceDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ThemePreferenceDataSourceImpl @Inject constructor(
    private val themeDataStoreManager: ThemeDataStoreManager
): ThemePreferenceDataSource {

    override fun getSelectedTheme(): Flow<String> = themeDataStoreManager.selectedTheme

    override suspend fun saveTheme(theme: String) = themeDataStoreManager.saveTheme(theme)
}
