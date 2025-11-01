package com.jpn.feature.appearance.data.datasource

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.jpn.core.designsystem.domain.ThemeDataStoreManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.themeDataStore by preferencesDataStore("theme_preferences")

class ThemeDataStoreManagerImpl @Inject constructor(@ApplicationContext private val context: Context) :
    ThemeDataStoreManager {

    companion object {
        private val SELECTED_THEME_KEY = stringPreferencesKey("selected_theme")
        private const val DEFAULT_THEME = "SUNNY"
    }

    override val selectedTheme: Flow<String> = context.themeDataStore.data.map { prefs ->
        prefs[SELECTED_THEME_KEY] ?: DEFAULT_THEME
    }

    override suspend fun saveTheme(theme: String) {
        context.themeDataStore.edit { prefs ->
            prefs[SELECTED_THEME_KEY] = theme
        }
    }
}
