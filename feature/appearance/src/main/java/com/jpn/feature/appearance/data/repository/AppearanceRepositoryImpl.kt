package com.jpn.feature.appearance.data.repository

import com.google.gson.Gson
import com.jpn.core.designsystem.domain.ThemePreferenceDataSource
import com.jpn.core.designsystem.theme.AppThemeType
import com.jpn.core.utils.managers.LogManager
import com.jpn.feature.appearance.domain.repository.AppearanceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppearanceRepositoryImpl @Inject constructor(
    private val dataSource: ThemePreferenceDataSource,
    private val logManager: LogManager,
    private val gson: Gson
) : AppearanceRepository {
    override fun getSelectedTheme(): Flow<AppThemeType> =
        dataSource.getSelectedTheme().map { name ->
            runCatching { AppThemeType.valueOf(name) }.getOrDefault(AppThemeType.SUNNY)
        }

    override suspend fun saveTheme(theme: AppThemeType) {
        dataSource.saveTheme(theme.name)
    }
}